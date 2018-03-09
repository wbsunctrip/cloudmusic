package com.ctrip.basebiz.cloudmusic.viewmodel;

import android.support.annotation.NonNull;

/**
 * Created by zhoujian on 2017/6/9.
 */

public class ConversationViewModel implements Cloneable, Comparable<ConversationViewModel> {

    private String ownerId;

    private String partnerId;

    private boolean remindMe;

    private String type;

    private String title;

    private String avatarUrl;

    private long lastActivityTime;

    private int unReadCount;

    private String message;

    /**
     * 置顶会话
     */
    private boolean isTopConversation;


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public boolean isRemindMe() {
        return remindMe;
    }

    public void setRemindMe(boolean remindMe) {
        this.remindMe = remindMe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLastActivityTime() {
        return lastActivityTime;
    }

    public void setLastActivityTime(long lastActivityTime) {
        this.lastActivityTime = lastActivityTime;
    }

    public int getUnReadCount() {
        return unReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        this.unReadCount = unReadCount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isTopConversation() {
        return isTopConversation;
    }

    public void setTopConversation(boolean topConversation) {
        isTopConversation = topConversation;
    }

    @Override
    public ConversationViewModel clone() throws CloneNotSupportedException {
        ConversationViewModel model = null;
        try {
            model = (ConversationViewModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return model;
    }

    @Override
    public int compareTo(@NonNull ConversationViewModel another) {
        long lastActivityTimeLong = lastActivityTime;
        long anotherLastActivityTimeLong = another.lastActivityTime;
        if (isTopConversation(this)) {
            if (isTopConversation(another)) {
                if (lastActivityTimeLong < anotherLastActivityTimeLong) {
                    return 1;
                } else if (lastActivityTimeLong > anotherLastActivityTimeLong) {
                    return -1;
                } else {
                    return 0;
                }
            }
            return -1;
        } else if (isTopConversation(another)) {
            return 1;
        }
        if (lastActivityTimeLong < anotherLastActivityTimeLong) {
            return 1;
        } else if (lastActivityTimeLong > anotherLastActivityTimeLong) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ConversationViewModel) {
            ConversationViewModel info = (ConversationViewModel) o;
            return getPartnerId().equalsIgnoreCase(info.getPartnerId());
        }
        return super.equals(o);
    }

    private boolean isTopConversation(ConversationViewModel model) {
        return model.isTopConversation();
    }

}
