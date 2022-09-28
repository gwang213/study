package common;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;

// JSFunction �겢�옒�뒪�뒗 硫붿떆吏� �븣由쇱갹�쓣 �쓣�슫 �썑
// �듅�젙 �럹�씠吏�濡� �씠�룞�븯�뒗 �뒪�겕由쏀듃 肄붾뱶瑜� 異붽��빐二쇰뒗 硫붿꽌�뱶瑜� �떞怨� �엳�떎.
public class JSFunction {
	// 硫붿떆吏� �븣由쇱갹�쓣 �쓣�슫 �썑 紐낆떆�븳 URL濡� �씠�룞�빀�땲�떎.
	public static void alertLocation(String msg, String url, JspWriter out) {
		try {
			String script = "" // �궫�엯�븷 �옄諛붿뒪�겕由쏀듃 肄붾뱶
					+ "<script>" + "	alert('" + msg + "');" + "	location.href='" + url + "';" + "</script>";
			out.println(script); // �옄諛붿뒪�겕由쏀듃 肄붾뱶瑜� out �궡�옣 媛앹껜濡� 異쒕젰(�궫�엯)
		} catch (Exception e) {
		}
	}

	// 硫붿떆吏� �븣由쇱갹�쓣 �쓣�슫 �썑 �씠�쟾 �럹�씠吏�濡� �룎�븘媛묐땲�떎.
	public static void alertBack(String msg, JspWriter out) {
		try {
			String script = "" + "<script>" + "	alert('" + msg + "');" + "	history.back();" + "</script>";
			out.println(script);
		} catch (Exception e) {
		}
	}

	// 硫붿떆吏� �븣由쇱갹�쓣 �쓣�슫 �썑 紐낆떆�븳 URL濡� �씠�룞�빀�땲�떎.
	public static void alertLocation(HttpServletResponse resp, String msg, String url) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" + "<script>" + "	alert('" + msg + "');" + "	location.href='" + url + "';" + "</script>";
			writer.print(script);
		} catch (Exception e) {
		}
	}

	// 硫붿떆吏� �븣由쇱갹�쓣 �쓣�슫 �썑 �씠�쟾 �럹�씠吏�濡� �룎�븘媛묐땲�떎.
	public static void alertBack(HttpServletResponse resp, String msg) {
		try {
			resp.setContentType("text/html; charset=UTF-8");
			PrintWriter writer = resp.getWriter();
			String script = "" + "<script>" + "	alert('" + msg + "');" + "	history.back();" + "</script>";
			writer.println(script);
		} catch (Exception e) {
		}
	}
}
