package jp.personal.pakuqi.chatworktrial.javabeans;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class RoomListData  implements Serializable {

    private static final long serialVersionUID = 6255752248513019027L;

    private int _roomId;
    private String _name;
    private String _iConPath;


    public int getRoomId() {
        return _roomId;
    }

    public void setRoomId(int roomId) {
        this._roomId = roomId;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getiConPath() {
        return _iConPath;
    }

    public void setiConPath(String iConPath) {
        this._iConPath = iConPath;
    }


}
