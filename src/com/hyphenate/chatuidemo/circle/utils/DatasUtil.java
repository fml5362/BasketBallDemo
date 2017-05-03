package com.hyphenate.chatuidemo.circle.utils;


import com.hyphenate.chatuidemo.circle.bean.CircleItem;
import com.hyphenate.chatuidemo.circle.bean.CommentItem;
import com.hyphenate.chatuidemo.circle.bean.FavortItem;
import com.hyphenate.chatuidemo.circle.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yiw
 * @ClassName: DatasUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015-12-28 下午4:16:21
 */
public class DatasUtil {
    public static final String[] CONTENTS = {"明天操场召开篮球赛，火箭队与小牛队强烈对抗，有报名的吗？", "一、活动名称：“友谊第一，比赛第二”篮球比赛  二、活动目的：篮球比赛是中国的传统体育活动，在比赛中能够很好的体现队员之间的团结协作能力，从而加强参赛团体之间的合作、交流，增强整个参赛队伍之间的凝聚力。为了加强部门与部门，员工与公司之间的联系，加深员工之间的友谊，特举行此次篮球比赛。  三、参赛对象：篮球爱好者在职员工", "今天是个好日子，天气不错，有打球的没", "呵呵，今天打球被完虐啊", "图不错",
            "我勒个去，打了一天的球，累成狗"};
    public static final String[] PHOTOS = {
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1493797899759&di=11625cddb807443b2695a6e8d7caae21&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fforum%2Fw%253D580%2Fsign%3D0ebcfd63212dd42a5f0901a3333a5b2f%2Fe62e11dfa9ec8a13bab1ffeaf303918fa0ecc010.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=429235647,268132490&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3346721308,539128563&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3140064774,2673655868&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2719064470,1161794942&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2371212661,1710967258&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3262821439,3490171191&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=193155294,415001578&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1887551697,3356711799&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=429235647,268132490&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1113280114,2992304793&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1363838375,2902689632&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=831251050,3150461679&fm=23&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1895379826,2564156552&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2459961813,1993022227&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3307072143,804823431&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=493096788,3940101369&fm=23&gp=0.jpg"};
    public static final String[] HEADIMG = {
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=4005501418,45289660&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3150900731,3826916764&fm=23&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3318350477,3422943506&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3547791706,3883005665&fm=23&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2369399569,698552273&fm=23&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3292250869,520145102&fm=23&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1792819640,3330346491&fm=23&gp=0.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2924909322,2821318999&fm=23&gp=0.jpg"};

    public static List<User> users = new ArrayList<User>();
    /**
     * 动态id自增长
     */
    private static int circleId = 0;
    /**
     * 点赞id自增长
     */
    private static int favortId = 0;
    /**
     * 评论id自增长
     */
    private static int commentId = 0;
    public static final User curUser = new User("0", "自己", HEADIMG[0]);

    static {
        User user1 = new User("1", "张三", HEADIMG[1]);
        User user2 = new User("2", "李四", HEADIMG[2]);
        User user3 = new User("3", "王磊", HEADIMG[3]);
        User user4 = new User("4", "赵六", HEADIMG[4]);
        User user5 = new User("5", "田七", HEADIMG[5]);
        User user6 = new User("6", "Naoki", HEADIMG[6]);
        User user7 = new User("7", "李云兴", HEADIMG[7]);

        users.add(curUser);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
    }

    public static List<CircleItem> createCircleDatas() {
        List<CircleItem> circleDatas = new ArrayList<CircleItem>();
        for (int i = 0; i < 15; i++) {
            CircleItem item = new CircleItem();
            User user = getUser();
            item.setId(String.valueOf(circleId++));
            item.setUser(user);
            item.setContent(getContent());
            item.setCreateTime("12月24日");

            item.setFavorters(createFavortItemList());
            item.setComments(createCommentItemList());
            if (getRandomNum(10) % 2 == 0) {
                item.setType("1");// 链接
                item.setLinkImg("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1867671507,168506371&fm=23&gp=0.jpg");
                item.setLinkTitle("篮球知识普及");
                item.setLinkUrl("http://ty.scetc.net/s/25/t/43/17/f1/info6129.htm");
            } else {
                item.setType("2");// 图片
                item.setPhotos(createPhotos());
            }
            circleDatas.add(item);
        }

        return circleDatas;
    }

    public static User getUser() {
        return users.get(getRandomNum(users.size()));
    }

    public static String getContent() {
        return CONTENTS[getRandomNum(CONTENTS.length)];
    }

    public static int getRandomNum(int max) {
        Random random = new Random();
        int result = random.nextInt(max);
        return result;
    }

    public static List<String> createPhotos() {
        List<String> photos = new ArrayList<String>();
        int size = getRandomNum(PHOTOS.length);
        if (size > 0) {
            if (size > 9) {
                size = 9;
            }
            for (int i = 0; i < size; i++) {
                String photo = PHOTOS[getRandomNum(PHOTOS.length)];
                if (!photos.contains(photo)) {
                    photos.add(photo);
                } else {
                    i--;
                }
            }
        }
        return photos;
    }

    public static List<FavortItem> createFavortItemList() {
        int size = getRandomNum(users.size());
        List<FavortItem> items = new ArrayList<FavortItem>();
        List<String> history = new ArrayList<String>();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                FavortItem newItem = createFavortItem();
                String userid = newItem.getUser().getId();
                if (!history.contains(userid)) {
                    items.add(newItem);
                    history.add(userid);
                } else {
                    i--;
                }
            }
        }
        return items;
    }

    public static FavortItem createFavortItem() {
        FavortItem item = new FavortItem();
        item.setId(String.valueOf(favortId++));
        item.setUser(getUser());
        return item;
    }

    public static FavortItem createCurUserFavortItem() {
        FavortItem item = new FavortItem();
        item.setId(String.valueOf(favortId++));
        item.setUser(curUser);
        return item;
    }

    public static List<CommentItem> createCommentItemList() {
        List<CommentItem> items = new ArrayList<CommentItem>();
        int size = getRandomNum(10);
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                items.add(createComment());
            }
        }
        return items;
    }

    public static CommentItem createComment() {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent("哈哈");
        User user = getUser();
        item.setUser(user);
        if (getRandomNum(10) % 2 == 0) {
            while (true) {
                User replyUser = getUser();
                if (!user.getId().equals(replyUser.getId())) {
                    item.setToReplyUser(replyUser);
                    break;
                }
            }
        }
        return item;
    }

    /**
     * 创建发布评论
     *
     * @return
     */
    public static CommentItem createPublicComment(String content) {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent(content);
        item.setUser(curUser);
        return item;
    }

    /**
     * 创建回复评论
     *
     * @return
     */
    public static CommentItem createReplyComment(User replyUser, String content) {
        CommentItem item = new CommentItem();
        item.setId(String.valueOf(commentId++));
        item.setContent(content);
        item.setUser(curUser);
        item.setToReplyUser(replyUser);
        return item;
    }
}
