package com.hyphenate.chatuidemo.circle;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.chatuidemo.R;
import com.hyphenate.chatuidemo.circle.adapter.CircleAdapter;
import com.hyphenate.chatuidemo.circle.bean.CircleItem;
import com.hyphenate.chatuidemo.circle.bean.CommentConfig;
import com.hyphenate.chatuidemo.circle.bean.CommentItem;
import com.hyphenate.chatuidemo.circle.bean.FavortItem;
import com.hyphenate.chatuidemo.circle.listener.SwpipeListViewOnScrollListener;
import com.hyphenate.chatuidemo.circle.mvp.presenter.CirclePresenter;
import com.hyphenate.chatuidemo.circle.mvp.view.ICircleView;
import com.hyphenate.chatuidemo.circle.utils.CommonUtils;
import com.hyphenate.chatuidemo.circle.utils.DatasUtil;
import com.hyphenate.chatuidemo.circle.widgets.CommentListView;
import com.hyphenate.chatuidemo.ui.MainActivity;

import java.util.List;

/**
 * 
* @ClassName: MainActivity 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yiw
* @date 2015-12-28 下午4:21:18 
*
 */
public class CircleMainActivity extends Fragment implements OnRefreshListener, ICircleView {

	protected static final String TAG = MainActivity.class.getSimpleName();
	private ListView mCircleLv;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private CircleAdapter mAdapter;
	private LinearLayout mEditTextBody;
	private EditText mEditText;
	private ImageView sendIv;

	private int mScreenHeight;
	private int mEditTextBodyHeight;
	private int mCurrentKeyboardH;
	private int mSelectCircleItemH;
	private int mSelectCommentItemOffset;

	private CirclePresenter mPresenter;
	private CommentConfig mCommentConfig;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_main, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mPresenter = new CirclePresenter(this);
		initView();
		loadData();
	}

	@SuppressLint({ "ClickableViewAccessibility", "InlinedApi" })
	private void initView() {
		mSwipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.mRefreshLayout);
		mCircleLv = (ListView) getView().findViewById(R.id.circleLv);
		mCircleLv.setOnScrollListener(new SwpipeListViewOnScrollListener(mSwipeRefreshLayout));
		mCircleLv.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (mEditTextBody.getVisibility() == View.VISIBLE) {
					//mEditTextBody.setVisibility(View.GONE);
					//CommonUtils.hideSoftInput(MainActivity.this, mEditText);
					updateEditTextBodyVisible(View.GONE, null);
					return true;
				}
				return false;
			}
		});
		mSwipeRefreshLayout.setOnRefreshListener(this);  
		mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);

		mAdapter = new CircleAdapter(getActivity());
		mAdapter.setCirclePresenter(mPresenter);
		mCircleLv.setAdapter(mAdapter);
		
		mEditTextBody = (LinearLayout) getView().findViewById(R.id.editTextBodyLl);
		mEditText = (EditText) getView().findViewById(R.id.circleEt);
		sendIv = (ImageView) getView().findViewById(R.id.sendIv);
		sendIv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mPresenter != null) {
					//发布评论
					String content = mEditText.getText().toString().trim();
					if(TextUtils.isEmpty(content)){
						Toast.makeText(getActivity(), "评论内容不能为空...", Toast.LENGTH_SHORT).show();
						return;
					}
					mPresenter.addComment(content, mCommentConfig);
				}
				updateEditTextBodyVisible(View.GONE, null);
			}
		});

		setViewTreeObserver();
	}


	private void setViewTreeObserver() {
		
		final ViewTreeObserver swipeRefreshLayoutVTO = mSwipeRefreshLayout.getViewTreeObserver();
		swipeRefreshLayoutVTO.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
            public void onGlobalLayout() {
            	
                Rect r = new Rect();
                mSwipeRefreshLayout.getWindowVisibleDisplayFrame(r);
				int statusBarH =  getStatusBarHeight();//状态栏高度
                int screenH = mSwipeRefreshLayout.getRootView().getHeight();
				if(r.top != statusBarH ){
					//在这个demo中r.top代表的是状态栏高度，在沉浸式状态栏时r.top＝0，通过getStatusBarHeight获取状态栏高度
					r.top = statusBarH;
				}
                int keyboardH = screenH - (r.bottom - r.top);
				Log.d(TAG, "screenH＝ "+ screenH +" &keyboardH = " + keyboardH + " &r.bottom=" + r.bottom + " &top=" + r.top + " &statusBarH=" + statusBarH);

                if(keyboardH == mCurrentKeyboardH){//有变化时才处理，否则会陷入死循环
                	return;
                }

				mCurrentKeyboardH = keyboardH;
            	mScreenHeight = screenH;//应用屏幕的高度
            	mEditTextBodyHeight = mEditTextBody.getHeight();

				//偏移listview
				if(mCircleLv!=null && mCommentConfig != null){
					int index = mCommentConfig.circlePosition==0?mCommentConfig.circlePosition:(mCommentConfig.circlePosition+mCircleLv.getHeaderViewsCount());
					mCircleLv.setSelectionFromTop(index, getListviewOffset(mCommentConfig));
				}
            }
        });
	}

	/**
	 * 获取状态栏高度
	 * @return
	 */
	private int getStatusBarHeight() {
		int result = 0;
		int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

	@Override
	public void onRefresh() {
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				loadData();
				mSwipeRefreshLayout.setRefreshing(false);
			}
		}, 2000);
		
	}

	private void loadData() {
		List<CircleItem> datas = DatasUtil.createCircleDatas(getActivity());
		mAdapter.setDatas(datas);
		mAdapter.notifyDataSetChanged();
	}



	@Override
	public void update2DeleteCircle(String circleId) {
		List<CircleItem> circleItems = mAdapter.getDatas();
		for(int i=0; i<circleItems.size(); i++){
			if(circleId.equals(circleItems.get(i).getId())){
				circleItems.remove(i);
				mAdapter.notifyDataSetChanged();
				return;
			}
		}
	}

	@Override
	public void update2AddFavorite(int circlePosition, FavortItem addItem) {
		if(addItem != null){
			mAdapter.getDatas().get(circlePosition).getFavorters().add(addItem);
			mAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void update2DeleteFavort(int circlePosition, String favortId) {
		List<FavortItem> items = mAdapter.getDatas().get(circlePosition).getFavorters();
		for(int i=0; i<items.size(); i++){
			if(favortId.equals(items.get(i).getId())){
				items.remove(i);
				mAdapter.notifyDataSetChanged();
				return;
			}
		}
	}

	@Override
	public void update2AddComment(int circlePosition, CommentItem addItem) {
		if(addItem != null){
			mAdapter.getDatas().get(circlePosition).getComments().add(addItem);
			mAdapter.notifyDataSetChanged();
		}
		//清空评论文本
		mEditText.setText("");
	}

	@Override
	public void update2DeleteComment(int circlePosition, String commentId) {
		List<CommentItem> items = mAdapter.getDatas().get(circlePosition).getComments();
		for(int i=0; i<items.size(); i++){
			if(commentId.equals(items.get(i).getId())){
				items.remove(i);
				mAdapter.notifyDataSetChanged();
				return;
			}
		}
	}

	@Override
	public void updateEditTextBodyVisible(int visibility, CommentConfig commentConfig) {
		mCommentConfig = commentConfig;
		mEditTextBody.setVisibility(visibility);

		measureCircleItemHighAndCommentItemOffset(commentConfig);

		if(View.VISIBLE==visibility){
			mEditText.requestFocus();
			//弹出键盘
			CommonUtils.showSoftInput(mEditText.getContext(), mEditText);

		}else if(View.GONE==visibility){
			//隐藏键盘
			CommonUtils.hideSoftInput(mEditText.getContext(), mEditText);
		}
	}


	/**
	 * 测量偏移量
	 * @param commentConfig
	 * @return
	 */
	private int getListviewOffset(CommentConfig commentConfig) {
		if(commentConfig == null)
			return 0;
		//这里如果你的listview上面还有其它占高度的控件，则需要减去该控件高度，listview的headview除外。
		int listviewOffset = mScreenHeight - mSelectCircleItemH - mCurrentKeyboardH - mEditTextBodyHeight;
		if(commentConfig.commentType == CommentConfig.Type.REPLY){
			//回复评论的情况
			listviewOffset = listviewOffset + mSelectCommentItemOffset;
		}
		return listviewOffset;
	}

	private void measureCircleItemHighAndCommentItemOffset(CommentConfig commentConfig){
		if(commentConfig == null)
			return;

		int headViewCount = mCircleLv.getHeaderViewsCount();
		int firstPosition = mCircleLv.getFirstVisiblePosition();
		//只能返回当前可见区域（列表可滚动）的子项
		View selectCircleItem = mCircleLv.getChildAt(headViewCount + commentConfig.circlePosition - firstPosition);
		if(selectCircleItem != null){
			mSelectCircleItemH = selectCircleItem.getHeight();
			if(headViewCount >0 && firstPosition <headViewCount && commentConfig.circlePosition == 0){
				//如果有headView，而且head是可见的，并且处理偏移的位置是第一条动态，则将显示的headView的高度合并到第一条动态上
				for(int i=firstPosition; i<headViewCount; i++){
					mSelectCircleItemH += mCircleLv.getChildAt(i).getHeight();
				}
			}
		}

		if(commentConfig.commentType == CommentConfig.Type.REPLY){
			//回复评论的情况
			CommentListView commentLv = (CommentListView) selectCircleItem.findViewById(R.id.commentList);
			if(commentLv!=null){
				//找到要回复的评论view,计算出该view距离所属动态底部的距离
				View selectCommentItem = commentLv.getChildAt(commentConfig.commentPosition);
				if(selectCommentItem != null){
					//选择的commentItem距选择的CircleItem底部的距离
					mSelectCommentItemOffset = 0;
					View parentView = selectCommentItem;
					do {
						int subItemBottom = parentView.getBottom();
						parentView = (View) parentView.getParent();
						if(parentView != null){
							mSelectCommentItemOffset += (parentView.getHeight() - subItemBottom);
						}
					} while (parentView != null && parentView != selectCircleItem);
				}
			}
		}
	}

}
