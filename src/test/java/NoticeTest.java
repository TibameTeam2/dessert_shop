import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.member.model.MemberBean;
import com.notice.controller.NoticeWS;
import com.notice.model.NoticeBean;
import com.notice.model.NoticeService;
import com.util.ResultInfo;

import cn.hutool.core.convert.Convert;

public class NoticeTest {

	@Test
	public void addNoticeTest() {

		boolean flag = addNotice("4", "push測試05", "amy");		
		System.out.println("flag = " + flag);
		
	}

	public Boolean addNotice(String notice_type, String notice_content, String member_account) {
		NoticeBean noticeBean = new NoticeBean();
		noticeBean.setNotice_type(Convert.toInt(notice_type));
		noticeBean.setNotice_content(notice_content);
		noticeBean.setRead_status(0);
		noticeBean.setMember_account(member_account);

		NoticeService noticeSvc = new NoticeService();

		Boolean flag = noticeSvc.addNotice(noticeBean);

		if (flag) {

			NoticeWS.sendCustomizeMessage(member_account, notice_content);
			return true;

		} else {
			return false;

		}
	}

}
