package com.syj.service.inter;

import java.util.List;

import com.syj.domain.Message;
import com.syj.domain.User;

public interface MessageServiceInterface {

	//显示用户接受到的所有 Message
	public List<Message> showMessage(User u);
}
