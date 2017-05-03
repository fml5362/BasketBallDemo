package com.hyphenate.chatuidemo.circle.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.DemoApplication;
import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.circle.ImagePagerActivity;
import com.hyphenate.chatuidemo.circle.bean.ActionItem;
import com.hyphenate.chatuidemo.circle.bean.CircleItem;
import com.hyphenate.chatuidemo.circle.bean.CommentConfig;
import com.hyphenate.chatuidemo.circle.bean.CommentItem;
import com.hyphenate.chatuidemo.circle.bean.FavortItem;
import com.hyphenate.chatuidemo.circle.mvp.presenter.CirclePresenter;
import com.hyphenate.chatuidemo.circle.spannable.ISpanClick;
import com.hyphenate.chatuidemo.circle.utils.DatasUtil;
import com.hyphenate.chatuidemo.circle.widgets.CircularImage;
import com.hyphenate.chatuidemo.circle.widgets.CommentListView;
import com.hyphenate.chatuidemo.circle.widgets.FavortListView;
import com.hyphenate.chatuidemo.circle.widgets.MultiImageView;
import com.hyphenate.chatuidemo.circle.widgets.SnsPopupWindow;
import com.hyphenate.chatuidemo.circle.widgets.dialog.CommentDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;

import java.util.ArrayList;
import java.util.List;
/**
 * 
* @ClassName: CircleAdapter 
* @Description: 圈子列表的adapter 
* @author yiw
* @date 2015-12-28 上午09:37:23 
*
 */
public class CircleAdapter extends BaseAdapter {
	private static final int ITEM_VIEW_TYPE_DEFAULT = 0;
	private static final int ITEM_VIEW_TYPE_URL = 1;
	private static final int ITEM_VIEW_TYPE_IMAGE = 2;

	
	private static final String ITEM_TYPE_URL = "1";
	private static final String ITEM_TYPE_IMAGE = "2";
	private static final int ITEM_VIEW_TYPE_COUNT = 3;
	
	private Context mContext;
	private CirclePresenter mPresenter;
	private List<CircleItem> datas = new ArrayList<CircleItem>();

	public void setCirclePresenter(CirclePresenter presenter){
		mPresenter = presenter;
	}
	
	public List<CircleItem> getDatas() {
		return datas;
	}
	public void setDatas(List<CircleItem> datas) {
		if(datas != null){
			this.datas = datas;
		}
	}
	
	public CircleAdapter(Context context){
		mContext = context;
	}
	
	@Override
	public int getItemViewType(int position) {
		int itemType = ITEM_VIEW_TYPE_DEFAULT;
		CircleItem item = datas.get(position);
		if (ITEM_TYPE_URL.equals(item.getType())) {
			itemType = ITEM_VIEW_TYPE_URL;
		} else if (ITEM_TYPE_IMAGE.equals(item.getType())) {
			itemType = ITEM_VIEW_TYPE_IMAGE;
		} else {
			itemType = ITEM_VIEW_TYPE_DEFAULT;
		}
		return itemType;
	}

	@Override
	public int getViewTypeCount() {
		return ITEM_VIEW_TYPE_COUNT;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		System.out.println("CircleAdaptr getView----------" + position);

		int itemViewType = getItemViewType(position);
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = View.inflate(mContext, R.layout.adapter_circle_item, null);
			ViewStub linkOrImgViewStub = (ViewStub) convertView.findViewById(R.id.linkOrImgViewStub);
			switch (itemViewType) {
			case ITEM_VIEW_TYPE_URL:// 链接view
				linkOrImgViewStub.setLayoutResource(R.layout.viewstub_urlbody);
				linkOrImgViewStub.inflate();
				LinearLayout urlBodyView = (LinearLayout) convertView.findViewById(R.id.urlBody);
				if(urlBodyView != null){
					holder.urlBody = urlBodyView;
					holder.urlImageIv = (ImageView) convertView.findViewById(R.id.urlImageIv);
					holder.urlContentTv = (TextView) convertView.findViewById(R.id.urlContentTv);
				}
				break;
			case ITEM_VIEW_TYPE_IMAGE:// 图片view
				linkOrImgViewStub.setLayoutResource(R.layout.viewstub_imgbody);
				linkOrImgViewStub.inflate();
				MultiImageView multiImageView = (MultiImageView) convertView.findViewById(R.id.multiImagView);
				if(multiImageView != null){
					holder.multiImageView = multiImageView;
				}
				break;
			default:
				break;
			}
			holder.headIv = (CircularImage) convertView.findViewById(R.id.headIv);
			holder.nameTv = (TextView) convertView.findViewById(R.id.nameTv);
			holder.digLine = convertView.findViewById(R.id.lin_dig);

			holder.contentTv = (TextView) convertView.findViewById(R.id.contentTv);
			holder.urlTipTv = (TextView) convertView.findViewById(R.id.urlTipTv);
			holder.timeTv = (TextView) convertView.findViewById(R.id.timeTv);
			holder.deleteBtn = (TextView) convertView.findViewById(R.id.deleteBtn);
			holder.snsBtn = (ImageView) convertView.findViewById(R.id.snsBtn);
			holder.favortListTv = (FavortListView) convertView.findViewById(R.id.favortListTv);

			holder.digCommentBody = (LinearLayout) convertView.findViewById(R.id.digCommentBody);

            holder.commentList = (CommentListView)convertView.findViewById(R.id.commentList);
            holder.commentAdapter = new CommentAdapter(mContext);
			holder.favortListAdapter = new FavortListAdapter();

			holder.favortListTv.setAdapter(holder.favortListAdapter);
			holder.commentList.setAdapter(holder.commentAdapter);

			holder.snsPopupWindow = new SnsPopupWindow(mContext);

			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		CircleItem circleItem = datas.get(position);
		final String circleId = circleItem.getId();
		String name = circleItem.getUser().getName();
		String headImg = circleItem.getUser().getHeadUrl();
		String content = circleItem.getContent();
		String createTime = circleItem.getCreateTime();
		final List<FavortItem> favortDatas = circleItem.getFavorters();
		final List<CommentItem> commentsDatas = circleItem.getComments();
		boolean hasFavort = circleItem.hasFavort();
		boolean hasComment = circleItem.hasComment();

		ImageLoader.getInstance().displayImage(headImg, holder.headIv);
		holder.nameTv.setText(name);
		holder.timeTv.setText(createTime);
		holder.contentTv.setText(content);
        holder.contentTv.setVisibility(TextUtils.isEmpty(content) ? View.GONE : View.VISIBLE);

		if(DatasUtil.curUser.getId().equals(circleItem.getUser().getId())){
			holder.deleteBtn.setVisibility(View.VISIBLE);
		}else{
			holder.deleteBtn.setVisibility(View.GONE);
		}
		holder.deleteBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//删除
				if(mPresenter!=null){
					mPresenter.deleteCircle(circleId);
				}
			}
		});
		if(hasFavort || hasComment){
			if(hasFavort){//处理点赞列表
				holder.favortListTv.setSpanClickListener(new ISpanClick() {
					@Override
					public void onClick(int position) {
						String userName = favortDatas.get(position).getUser().getName();
						String userId = favortDatas.get(position).getUser().getId();
						Toast.makeText(DemoApplication.getContext(), userName + " &id = " + userId, Toast.LENGTH_SHORT).show();
					}
				});
				holder.favortListAdapter.setDatas(favortDatas);
				holder.favortListAdapter.notifyDataSetChanged();
				holder.favortListTv.setVisibility(View.VISIBLE);
			}else{
				holder.favortListTv.setVisibility(View.GONE);
			}

			if(hasComment){//处理评论列表
                holder.commentList.setOnItemClick(new CommentListView.OnItemClickListener() {
                    @Override
                    public void onItemClick(int commentPosition) {
                        CommentItem commentItem = commentsDatas.get(commentPosition);
                        if(DatasUtil.curUser.getId().equals(commentItem.getUser().getId())){//复制或者删除自己的评论

                            CommentDialog dialog = new CommentDialog(mContext, mPresenter, commentItem, position);
                            dialog.show();
                        }else{//回复别人的评论
                            if(mPresenter != null){
                                CommentConfig config = new CommentConfig();
                                config.circlePosition = position;
                                config.commentPosition = commentPosition;
                                config.commentType = CommentConfig.Type.REPLY;
                                config.replyUser = commentItem.getUser();
                                mPresenter.showEditTextBody(config);
                            }
                        }
                    }
                });
                holder.commentList.setOnItemLongClick(new CommentListView.OnItemLongClickListener() {
                    @Override
                    public void onItemLongClick(int commentPosition) {
                        //长按进行复制或者删除
                        CommentItem commentItem = commentsDatas.get(commentPosition);
                        CommentDialog dialog = new CommentDialog(mContext, mPresenter, commentItem, position);
                        dialog.show();
                    }
                });
                holder.commentAdapter.setDatas(commentsDatas);
                holder.commentAdapter.notifyDataSetChanged();
                holder.commentList.setVisibility(View.VISIBLE);

			}else {

				holder.commentList.setVisibility(View.GONE);
			}
			holder.digCommentBody.setVisibility(View.VISIBLE);
		}else{
			holder.digCommentBody.setVisibility(View.GONE);
		}

		holder.digLine.setVisibility(hasFavort && hasComment ? View.VISIBLE : View.GONE);

		final SnsPopupWindow snsPopupWindow = holder.snsPopupWindow;
		//判断是否已点赞
		String curUserFavortId = circleItem.getCurUserFavortId(DatasUtil.curUser.getId());
		if(!TextUtils.isEmpty(curUserFavortId)){
			snsPopupWindow.getmActionItems().get(0).mTitle = "取消";
		}else{
			snsPopupWindow.getmActionItems().get(0).mTitle = "赞";
		}
		snsPopupWindow.update();
		snsPopupWindow.setmItemClickListener(new PopupItemClickListener(position, circleItem, curUserFavortId));
		holder.snsBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View view) {
				//弹出popupwindow
				snsPopupWindow.showPopupWindow(view);
			}
		});

		holder.urlTipTv.setVisibility(View.GONE);
		switch (itemViewType) {
		case ITEM_VIEW_TYPE_URL:// 处理链接动态的链接内容和和图片
			String linkImg = circleItem.getLinkImg();
			String linkTitle = circleItem.getLinkTitle();
			ImageLoader.getInstance().displayImage(linkImg, holder.urlImageIv);
			holder.urlContentTv.setText(linkTitle);
			holder.urlBody.setVisibility(View.VISIBLE);
			holder.urlTipTv.setVisibility(View.VISIBLE);
			break;
		case ITEM_VIEW_TYPE_IMAGE:// 处理图片
			final List<String> photos = circleItem.getPhotos();
			if (photos != null && photos.size() > 0) {
				holder.multiImageView.setVisibility(View.VISIBLE);
				holder.multiImageView.setList(photos);
				holder.multiImageView.setOnItemClickListener(new MultiImageView.OnItemClickListener() {
					@Override
					public void onItemClick(View view, int position) {
						// 因为单张图片时，图片实际大小是自适应的，imageLoader缓存时是按测量尺寸缓存的
						ImagePagerActivity.imageSize = new ImageSize(view.getMeasuredWidth(), view.getMeasuredHeight());
						ImagePagerActivity.startImagePagerActivity(mContext, photos, position);
					}
				});
			} else {
				holder.multiImageView.setVisibility(View.GONE);
			}
			break;
		default:
			break;
		}
		return convertView;
	}

	class ViewHolder {
		public CircularImage headIv;
		public TextView nameTv;
		public TextView urlTipTv;
		/** 动态的内容 */
		public TextView contentTv;
		public TextView timeTv;
		public TextView deleteBtn;
		public ImageView snsBtn;
		/** 点赞列表*/
		public FavortListView favortListTv;
		
		public LinearLayout urlBody;
		public LinearLayout digCommentBody;
		public View digLine;

		/** 评论列表 */
        public CommentListView commentList;
		/** 链接的图片 */
		public ImageView urlImageIv;
		/** 链接的标题 */
		public TextView urlContentTv;
		/** 图片*/
		public MultiImageView multiImageView;
		// ===========================
		public FavortListAdapter favortListAdapter;
		//public CommentAdapter bbsAdapter;
        public CommentAdapter commentAdapter;
		public SnsPopupWindow snsPopupWindow;
	}
	
	private class PopupItemClickListener implements SnsPopupWindow.OnItemClickListener{
		private String mFavorId;
		//动态在列表中的位置
		private int mCirclePosition;
		private long mLasttime = 0;
		private CircleItem mCircleItem;

		public PopupItemClickListener(int circlePosition, CircleItem circleItem, String favorId){
			this.mFavorId = favorId;
			this.mCirclePosition = circlePosition;
			this.mCircleItem = circleItem;
		}

		@Override
		public void onItemClick(ActionItem actionitem, int position) {
			switch (position) {
			case 0://点赞、取消点赞
				if(System.currentTimeMillis()-mLasttime<700)//防止快速点击操作
					return;
				mLasttime = System.currentTimeMillis();
				if(mPresenter != null){
					if ("赞".equals(actionitem.mTitle.toString())) {
						mPresenter.addFavort(mCirclePosition);
					} else {//取消点赞
						mPresenter.deleteFavort(mCirclePosition, mFavorId);
					}
				}
				break;
			case 1://发布评论
				if(mPresenter != null){
					CommentConfig config = new CommentConfig();
					config.circlePosition = mCirclePosition;
					config.commentType = CommentConfig.Type.PUBLIC;
					mPresenter.showEditTextBody(config);
				}
				break;
			default:
				break;
			}
		}
	}

}
