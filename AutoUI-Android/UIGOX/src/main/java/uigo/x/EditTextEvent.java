/*Copyright ©2025 TommyLemon(https://github.com/TommyLemon/UIGOX)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package uigo.x;

import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

public class EditTextEvent extends KeyEvent {
    public static final int WHEN_BEFORE = -1;
    public static final int WHEN_ON = 0;
    public static final int WHEN_AFTER = 1;

    public static final UIAutoApp APP = UIAutoApp.getInstance();

    EditText target;

    public static String getWhenName(int when) {
        switch (when) {
            case WHEN_BEFORE:
                return "BEFORE";
            case WHEN_AFTER:
                return "AFTER";
            default:
                return "ON";
        }
    }

    String text;
    public String getText() {
        if (text == null) {
            text = StringUtil.getString(getS()); // target 文本在变，不稳定 StringUtil.getString(target == null ? getS() : target.getText());
        }
        return text;
    }

    int selectStart;
    public int getSelectStart() {
        return selectStart;
    }

    int selectEnd;
    public int getSelectEnd() {
        return selectEnd;
    }

    public EditText getTarget() {
        boolean isWeb = StringUtil.isNotEmpty(targetWebId, true);
        if (target == null && isWeb) {
            target = APP.findView(targetWebId);
        }

        int id = APP.getResId(targetIdName);
        if (id <= 0) {
            id = targetId;
        }

        if (target == null || (isWeb == false && target.isAttachedToWindow() == false)) {
            View v = id >= 0 ? null : APP.findView(id);
            target = v instanceof EditText ? (EditText) v : target;
            if (target == null && targetId != id && targetId > 0) {
                v = APP.findView(targetId);
                target = v instanceof EditText ? (EditText) v : target;
            }
        }

        if (target == null) {
            Integer x = getX();
            Integer y = getY();

            View decorView = APP.getCurrentDecorView();
            target = x == null || y == null ? null : APP.findViewByPoint(decorView, EditText.class, x, y, true);
            if (target == null) {
                UIAutoApp.NearestView<EditText> nv = APP.findNearestView(decorView, EditText.class, x == null ? 0 : x, y == null ? 0 : y, false, id, null, null);
                target = nv == null ? target : nv.view;

                if (target == null) {
                    target = APP.findViewByFocus(decorView, EditText.class);
                }
            }
        }

        return target;
    }

    String targetType;
    public String getTargetType() {
        if (StringUtil.isEmpty(targetType)) {
            target = getTarget();
            targetType = target == null ? null : target.getClass().getName();
        }
        return targetType;
    }

    int targetId;
    public int getTargetId() {
        if (targetId <= 0) {
            target = getTarget();
            targetId = target == null ? targetId : target.getId();
        }
        return targetId;
    }

    String targetIdName;
    public String getTargetIdName() {
        if (StringUtil.isEmpty(targetIdName)) {
            targetIdName = APP.getResIdName(getTargetId());
        }
        return targetIdName;
    }
    public EditTextEvent setTargetIdName(String targetIdName) {
        this.targetIdName = targetIdName;
        return this;
    }

    String targetWebId;
    public String getTargetWebId() {
        return targetWebId;
    }
    public EditTextEvent setTargetWebId(String targetWebId) {
        this.targetWebId = targetWebId;
        return this;
    }

    Integer x;
    Integer y;
    public Integer getX() {
        return x;
    }
    public EditTextEvent setX(Integer x) {
        this.x = x;
        return this;
    }
    public Integer getY() {
        return y;
    }
    public EditTextEvent setY(Integer y) {
        this.y = y;
        return this;
    }

    int when;
    public int getWhen() {
        return when;
    }

    CharSequence s;
    public CharSequence getS() {
        return s;
    }


    int start;
    public int getStart() {
        return start;
    }

    int length;
    public int getLength() {
        return length;
    }

    int after;
    public int getAfter() {
        return after;
    }

    public EditTextEvent(int action, int code) {
        super(action, code);
    }

    public EditTextEvent(long downTime, long eventTime, int action,
                         int code, int repeat) {
        super(downTime, eventTime, action, code, repeat);
    }

    public EditTextEvent(long downTime, long eventTime, int action,
                         int code, int repeat, int metaState) {
        super(downTime, eventTime, action, code, repeat, metaState);
    }

    public EditTextEvent(long downTime, long eventTime, int action,
                         int code, int repeat, int metaState,
                         int deviceId, int scancode) {
        super(downTime, eventTime, action, code, repeat, metaState, deviceId, scancode);
    }

    public EditTextEvent(long downTime, long eventTime, int action,
                         int code, int repeat, int metaState,
                         int deviceId, int scancode, int flags) {
        super(downTime, eventTime, action, code, repeat, metaState, deviceId, scancode, flags);
    }

    public EditTextEvent(long downTime, long eventTime, int action,
                         int code, int repeat, int metaState,
                         int deviceId, int scancode, int flags, int source) {
        super(downTime, eventTime, action, code, repeat, metaState, deviceId, scancode, flags, source);
    }

    public EditTextEvent(long time, String characters, int deviceId, int flags) {
        super(time, characters, deviceId, flags);
    }

    public EditTextEvent(KeyEvent origEvent) {
        super(origEvent);
    }

    public EditTextEvent(int action, int code, EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s) {
        super(System.currentTimeMillis(), System.currentTimeMillis(), action, code, 0);
        init(target, when, text, selectStart, selectEnd, s);
    }
    public EditTextEvent(int action, int code, EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s, int start, int length) {
        super(System.currentTimeMillis(), System.currentTimeMillis(), action, code, 0);
        init(target, when, text, selectStart, selectEnd, s, start, length);
    }
    public EditTextEvent(int action, int code, EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s, int start, int length, int after) {
        super(System.currentTimeMillis(), System.currentTimeMillis(), action, code, 0);
        init(target, when, text, selectStart, selectEnd, s, start, length, after);
    }
    public EditTextEvent(long downTime, long eventTime, int action, int code, int repeat
            , EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s, int start, int length, int after) {
        super(downTime, eventTime, action, code, repeat);
        init(target, when, text, selectStart, selectEnd, s, start, length, after);
    }
    public EditTextEvent(
            long downTime, long eventTime, int action
            , int code, int repeat, int metaState
            , int deviceId, int scancode, int flags, int source
            , EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s, int start, int length, int after
    ) {
        super(downTime, eventTime, action, code, repeat, metaState, deviceId, scancode, flags, source);
        init(target, when, text, selectStart, selectEnd, s, start, length, after);
    }

    public void init(EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s) {
        init(target, when, text, selectStart, selectEnd, s, 0, 0, 0);
    }
    public void init(EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s, int start, int count) {
        init(target, when, text, selectStart, selectEnd, s, start, count, 0);
    }
    public void init(EditText target, int when, String text, int selectStart, int selectEnd, CharSequence s, int start, int count, int after) {
        this.target = target;
        this.targetId = target == null ? View.NO_ID : target.getId();
        this.targetIdName = APP.getResIdName(this.targetId);
        this.when = when;
        this.text = text;
        this.selectStart = selectStart;
        this.selectEnd = selectEnd;
        this.s = s;
        this.start = start;
        this.length = count;
        this.after = after;
    }


}
