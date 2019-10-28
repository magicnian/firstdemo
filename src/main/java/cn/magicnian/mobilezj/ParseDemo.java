package cn.magicnian.mobilezj;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.CollectionUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseDemo {

    private static String html = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />\n" +
            "<title>中国移动通信客户账单--全球通</title>\n" +
            "<STYLE>\n" +
            " body{ \n" +
            "\tmargin:0px; \n" +
            "\tpadding:0xp;\n" +
            "\tbackground:#e8e8e8;\n" +
            "}\n" +
            "th {\n" +
            "\tfont-size: 12px\n" +
            "}\n" +
            "td {\n" +
            "\tfont-size: 12px\n" +
            "}\n" +
            "h3 {\n" +
            "\tpadding-bottom: 0px; line-height: 16px; margin: 0px; padding-left: 0px; padding-right: 0px; color: #000; font-size: 12px; font-weight: bold; padding-top: 0px\n" +
            "}\n" +
            "a:link {\n" +
            "\tcolor: #333230\n" +
            "}\n" +
            "a:visited {\n" +
            "\tcolor: #333230\n" +
            "}\n" +
            "a:active {\n" +
            "\tcolor: #333230\n" +
            "}\n" +
            "a:hover {\n" +
            "\tcolor: #f00\n" +
            "}\n" +
            ".main {\n" +
            "\tmargin: 0px auto; min-height: 1000px; width: 780px; font: 12px/20px \"宋体\"; background: #f7f6f1; color: #63625d\n" +
            "}\n" +
            ".num_r {\n" +
            "\ttext-align: right; text-indent: 0px; padding-right: 10px\n" +
            "}\n" +
            ".fBlack {\n" +
            "\tcolor: #010100\n" +
            "}\n" +
            ".tb3 .fBlack {\n" +
            "\tcolor: #010100\n" +
            "}\n" +
            ".f_imp {\n" +
            "\tcolor: #fae500\n" +
            "}\n" +
            ".tb15 .f_imp {\n" +
            "\tcolor: #fae500\n" +
            "}\n" +
            ".money {\n" +
            "\tcolor: #fc7f2f\n" +
            "}\n" +
            "a img {\n" +
            "\tborder-bottom: medium none; border-left: medium none; border-top: medium none; border-right: medium none\n" +
            "}\n" +
            ".top {\n" +
            "\twidth: 780px; background: url(../images/gotone-pt/top.jpg) no-repeat left top; height: 181px\n" +
            "}\n" +
            ".pagetop{\n" +
            "\twidth: 780px; background: url(../images/gotone-pt/pagetop.jpg) no-repeat left top; height: 81px\n" +
            "}\n" +
            ".center {\n" +
            "\tmargin: 0px 35px; width: 710px; padding-top: 10px\n" +
            "}\n" +
            ".clear {\n" +
            "\theight: 1px; clear: both; overflow: hidden\n" +
            "}\n" +
            ".w {\n" +
            "\ttext-align: right; font: italic 14px/60px tahoma, \"simsun\"; color: #9e955c\n" +
            "}\n" +
            ".div1 {\n" +
            "\tmargin: 10px 0px 0px; width: 710px; clear: both\n" +
            "}\n" +
            ".div1 table {\n" +
            "\twidth: 330px; float: left\n" +
            "}\n" +
            ".div1 th {\n" +
            "\ttext-align: left; width: 65px; font: 12px/22px tahoma, \"simsun\"; color: #000\n" +
            "}\n" +
            ".div1 td {\n" +
            "\tcolor: #000\n" +
            "}\n" +
            ".div1 .padding30 {\n" +
            "\tpadding-left: 30px\n" +
            "}\n" +
            ".tb2 th {\n" +
            "\twidth: auto\n" +
            "}\n" +
            ".div1 .tb2 {\n" +
            "\twidth: 250px; float: right\n" +
            "}\n" +
            ".div1 .tb2 th {\n" +
            "\tcolor: #d20014; font-size: 12px\n" +
            "}\n" +
            ".div1 .tb2 .money {\n" +
            "\tfont-family: \"宋体\"; color: #d20014; font-size: 12px\n" +
            "}\n" +
            ".div2 {\n" +
            "\ttext-align: center; line-height: 25px; width: 710px; margin-bottom: 5px; background: #9e8753; float: left; color: #f7f6f1; clear: both\n" +
            "}\n" +
            ".div3 {\n" +
            "\tclear: both\n" +
            "}\n" +
            ".div3 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div3 p {\n" +
            "\tpadding-bottom: 10px; padding-left: 0px; padding-right: 0px; color: #4b4a46; clear: both; padding-top: 10px\n" +
            "}\n" +
            ".div3 .f_l {\n" +
            "\twidth: 220px; float: left\n" +
            "}\n" +
            ".gray td {\n" +
            "\tbackground: #ece8dc\n" +
            "}\n" +
            ".gray th {\n" +
            "\tbackground: #ece8dc\n" +
            "}\n" +
            ".tb3 {\n" +
            "\twidth: 480px; float: left; color: #010100\n" +
            "}\n" +
            ".tb3_2 {\n" +
            "\twidth: 480px; float: left; color: #010100\n" +
            "}\n" +
            ".tb3 td {\n" +
            "\tcolor: #63625d\n" +
            "}\n" +
            ".tb3 .centerline {\n" +
            "\tbackground: #f7f6f1\n" +
            "}\n" +
            ".tb3 th.fBlack {\n" +
            "\ttext-indent: 10px\n" +
            "}\n" +
            ".tb3 th {\n" +
            "\ttext-indent: 25px; font: 12px tahoma, \"simsun\"; height: 24px\n" +
            "}\n" +
            ".tb3 th.align_r {\n" +
            "\ttext-align: right; padding-right: 10px\n" +
            "}\n" +
            ".tb3 th.totalTd {\n" +
            "\ttext-indent: 10px\n" +
            "}\n" +
            ".tb3_2 {\n" +
            "\tborder-left: #f7f6f1 6px solid\n" +
            "}\n" +
            ".totalTd {\n" +
            "\tbackground: #89B929; color: #f7f6f1; font-weight: normal\n" +
            "}\n" +
            ".tb5 .totalTd {\n" +
            "\ttext-align: center; background: #89B929; color: #f7f6f1\n" +
            "}\n" +
            ".tb3 .totalTd {\n" +
            "\tline-height: 24px; background: #89B929; height: 24px; color: #f7f6f1\n" +
            "}\n" +
            ".tb5 .totalTd {\n" +
            "\ttext-align: left; text-indent: 10px\n" +
            "}\n" +
            ".tb4 {\n" +
            "\twidth: 710px; margin-bottom: 25px\n" +
            "}\n" +
            ".tb4 .totalTd {\n" +
            "\tline-height: 24px; text-indent: 10px; padding-right: 10px; height: 24px\n" +
            "}\n" +
            ".tb4 .fBlack {\n" +
            "\tborder-bottom: #f7f6f2 1px solid; text-align: left; line-height: 24px; text-indent: 10px; background: #ece8dc; height: 24px\n" +
            "}\n" +
            ".tb4 .num_r {\n" +
            "\tborder-bottom: #f7f6f2 1px solid; line-height: 24px; padding-right: 10px; background: #ece8dc; height: 24px\n" +
            "}\n" +
            ".middlepic div {\n" +
            "\tline-height: 19px\n" +
            "}\n" +
            ".middlepic h3 {\n" +
            "\tpadding-bottom: 5px\n" +
            "}\n" +
            ".totalTd .money {\n" +
            "\tpadding-bottom: 2px; padding-left: 2px; padding-right: 2px; background: #eef1e2; margin-left: 4px; padding-top: 2px\n" +
            "}\n" +
            ".div4 {\n" +
            "\tline-height: 32px; width: 710px; color: #333230; clear: both; padding-top: 20px\n" +
            "}\n" +
            ".div4 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div4 .tab_jf {\n" +
            "\twidth: 710px\n" +
            "}\n" +
            ".div4 .tab_jf th {\n" +
            "\tline-height: 28px; background: #89B929; height: 28px; color: #f7f6f1; font-weight: normal\n" +
            "}\n" +
            ".div4 .tab_jf td {\n" +
            "\ttext-align: center; line-height: 24px; background: #ece8dc; height: 24px; border-top: #f7f6f2 1px solid\n" +
            "}\n" +
            ".div5 {\n" +
            "\tpadding-bottom: 0px; line-height: 21px; padding-left: 0px; width: 710px; padding-right: 0px; color: #63625d; clear: both; padding-top: 10px\n" +
            "}\n" +
            ".div5 .memo {\n" +
            "\tcolor: #aa9a75\n" +
            "}\n" +
            ".div5 li {\n" +
            "\tpadding-bottom: 0px; margin: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px\n" +
            "}\n" +
            ".div5 ul {\n" +
            "\tpadding-bottom: 0px; margin: 0px; padding-left: 0px; padding-right: 0px; padding-top: 0px\n" +
            "}\n" +
            ".div5 li {\n" +
            "\tlist-style-type: none; margin-top: 5px; width: 296px; float: left; color: #333230; list-style-image: none\n" +
            "}\n" +
            ".div5 .num {\n" +
            "\tpadding-left: 10px; width: 20px\n" +
            "}\n" +
            ".divcell1 {\n" +
            "\twidth: 347px; padding-right: 15px; margin-bottom: 12px; float: left\n" +
            "}\n" +
            ".divcell2 {\n" +
            "\twidth: 347px; padding-right: 15px; margin-bottom: 12px; float: left\n" +
            "}\n" +
            ".divcell2 {\n" +
            "\tpadding-right: 0px\n" +
            "}\n" +
            ".celltit {\n" +
            "\tpadding-left: 5px; background: #9e8753; color: #fff\n" +
            "}\n" +
            ".memocon {\n" +
            "\tbackground: #ece8dc; height: 320px\n" +
            "}\n" +
            ".expand {\n" +
            "\tmargin-bottom: 10px\n" +
            "}\n" +
            ".expand td {\n" +
            "\tborder-bottom: #f7f6f1 1px solid; text-indent: 10px; background: #9e8753; color: #fff\n" +
            "}\n" +
            ".expand a:link {\n" +
            "\tpadding-bottom: 2px; padding-left: 2px; padding-right: 2px; color: #fff; text-decoration: none; padding-top: 2px\n" +
            "}\n" +
            ".expand a:active {\n" +
            "\tpadding-bottom: 2px; padding-left: 2px; padding-right: 2px; color: #fff; text-decoration: none; padding-top: 2px\n" +
            "}\n" +
            ".expand a:visited {\n" +
            "\tpadding-bottom: 2px; padding-left: 2px; padding-right: 2px; color: #fff; text-decoration: none; padding-top: 2px\n" +
            "}\n" +
            ".expand a:hover {\n" +
            "\tpadding-bottom: 2px; padding-left: 2px; padding-right: 2px; background: #f7f6f1; color: #9e8753; text-decoration: none; padding-top: 2px\n" +
            "}\n" +
            ".div6_1 {\n" +
            "\twidth: 450px; float: left; margin-right: 10px\n" +
            "}\n" +
            ".title1 {\n" +
            "\tmargin: 20px 0px 0px; width: 710px; float: left; height: 32px\n" +
            "}\n" +
            ".div6 {\n" +
            "\tpadding-bottom: 40px; margin: 0px; width: 710px; clear: both\n" +
            "}\n" +
            ".div6 .tb6 td {\n" +
            "\ttext-align: left; padding-bottom: 6px; line-height: 24px; padding-left: 10px; padding-right: 0px; background: #ece8dc; padding-top: 6px\n" +
            "}\n" +
            ".div6 #img2 {\n" +
            "\tfloat: right\n" +
            "}\n" +
            ".div6 .div6_icon {\n" +
            "\tpadding-bottom: 0px; padding-left: 0px; padding-right: 0px; clear: both; padding-top: 20px\n" +
            "}\n" +
            ".div7 {\n" +
            "\twidth: 710px; float: left\n" +
            "}\n" +
            ".div6 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div7 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div6 span font {\n" +
            "\tfont-size: 12px; font-weight: normal\n" +
            "}\n" +
            ".div7 span font {\n" +
            "\tfont-size: 12px; font-weight: normal\n" +
            "}\n" +
            ".div7 p {\n" +
            "\tpadding-bottom: 10px; padding-left: 0px; padding-right: 0px; color: #4b4a46; clear: both; padding-top: 10px\n" +
            "}\n" +
            ".div7 .memo td {\n" +
            "\tbackground: #ece8dc\n" +
            "}\n" +
            ".div7 .gray td {\n" +
            "\tborder-bottom: #f7f6f1 1px solid; background: #ece8dc\n" +
            "}\n" +
            ".div7 .gray th {\n" +
            "\tborder-bottom: #f7f6f1 1px solid; background: #ece8dc\n" +
            "}\n" +
            ".div9 .gray td {\n" +
            "\tborder-bottom: #f7f6f1 1px solid; background: #ece8dc\n" +
            "}\n" +
            ".div9 .gray th {\n" +
            "\tborder-bottom: #f7f6f1 1px solid; background: #ece8dc\n" +
            "}\n" +
            ".div7 .tb3 .gray td {\n" +
            "\tborder-bottom: 0px\n" +
            "}\n" +
            ".div7 .tb3 .gray th {\n" +
            "\tborder-bottom: 0px\n" +
            "}\n" +
            ".div7 .gray td {\n" +
            "\tline-height: 24px\n" +
            "}\n" +
            ".div7 .tb5 .gray td {\n" +
            "\ttext-indent: 10px\n" +
            "}\n" +
            ".div7 .tb3 {\n" +
            "\twidth: 100%\n" +
            "}\n" +
            ".div7 .tb3 .centerline {\n" +
            "\twidth: 10px; background: #f7f6f1\n" +
            "}\n" +
            ".div7 .tb3 td.align_r {\n" +
            "\ttext-align: right; padding-right: 10px\n" +
            "}\n" +
            ".div7 .tb5 .totalTd {\n" +
            "\tline-height: 24px; height: 24px\n" +
            "}\n" +
            ".div7 .tb3 .padding18 {\n" +
            "\tpadding-left: 18px\n" +
            "}\n" +
            ".tb5 {\n" +
            "\twidth: 710px; margin-bottom: 12px; clear: both\n" +
            "}\n" +
            ".td5 b {\n" +
            "\tcolor: #000\n" +
            "}\n" +
            ".tb5 th .fBlack {\n" +
            "\ttext-indent: 10px; background: #f7f6f1\n" +
            "}\n" +
            ".tb5 th {\n" +
            "\ttext-indent: 25px; font: 12px tahoma, \"simsun\"; height: 24px\n" +
            "}\n" +
            ".tb5 td {\n" +
            "\t\n" +
            "}\n" +
            ".div8 {\n" +
            "\tmargin-top: 8px; width: 710px; float: left\n" +
            "}\n" +
            ".div8 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div8 p {\n" +
            "\tpadding-bottom: 10px; padding-left: 0px; padding-right: 0px; color: #4b4a46; clear: both; padding-top: 10px\n" +
            "}\n" +
            ".div8 .gray td {\n" +
            "\ttext-align: center\n" +
            "}\n" +
            ".td4 b {\n" +
            "\tcolor: #000\n" +
            "}\n" +
            ".td5 b {\n" +
            "\tcolor: #000\n" +
            "}\n" +
            ".div9 {\n" +
            "\twidth: 710px; float: left\n" +
            "}\n" +
            ".div8 .totalTd {\n" +
            "\ttext-align: center; font-weight: normal\n" +
            "}\n" +
            ".div9 .totalTd {\n" +
            "\ttext-align: center; font-weight: normal\n" +
            "}\n" +
            ".div8 .tb7 {\n" +
            "\tline-height: 24px; width: 710px; margin-bottom: 20px\n" +
            "}\n" +
            ".div8 .tb7 td {\n" +
            "\tborder-bottom: #f7f6f2 1px solid; text-align: left; padding-left: 10px\n" +
            "}\n" +
            ".div9 .tb8 {\n" +
            "\tline-height: 24px; width: 710px; margin-bottom: 10px\n" +
            "}\n" +
            ".div9 .tb8 td {\n" +
            "\t\n" +
            "}\n" +
            ".div9 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div9 p {\n" +
            "\tpadding-bottom: 10px; padding-left: 0px; padding-right: 0px; color: #4b4a46; clear: both; padding-top: 10px\n" +
            "}\n" +
            ".div9 .gray td {\n" +
            "\tpadding-left: 10px\n" +
            "}\n" +
            ".div10 {\n" +
            "\tpadding-bottom: 20px; padding-left: 0px; padding-right: 0px; clear: both; padding-top: 0px\n" +
            "}\n" +
            ".div10 span {\n" +
            "\tline-height: 30px; color: #1365af; font-size: 14px; font-weight: bold\n" +
            "}\n" +
            ".div10 .gray {\n" +
            "\tpadding-bottom: 0px; padding-left: 10px; padding-right: 10px; background: #ece8dc; color: #000; padding-top: 0px\n" +
            "}\n" +
            ".div10 i {\n" +
            "\tfont-family: \"微软雅黑\", \"宋体\"; float: right; color: #9e8753; font-size: 14px\n" +
            "}\n" +
            ".div10 table {\n" +
            "\tmargin-bottom: 10px\n" +
            "}\n" +
            ".tb15 {\n" +
            "\twidth: 100%\n" +
            "}\n" +
            ".tb15 td {\n" +
            "\tborder-bottom: #e3decb 1px solid; text-indent: 1em; background: #ab9178\n" +
            "}\n" +
            ".tb15 a {\n" +
            "\tpadding-bottom: 2px; padding-left: 2px; padding-right: 2px; color: #f7f6f1; text-decoration: none; padding-top: 2px\n" +
            "}\n" +
            ".tb15 a:hover {\n" +
            "\tbackground: #f7f6f1; color: #ab9178\n" +
            "}\n" +
            ".f_imp {\n" +
            "\tcolor: #fae500\n" +
            "}\n" +
            ".td1 {\n" +
            "\twidth: 120px\n" +
            "}\n" +
            "</STYLE><script SRC='http://www.zj.10086.cn/js/sdc_zj.js' type='text/javascript'></script><META name='WT.si_n' content='IQ_YZDCX'><META name='WT.si_x' content='99'><META name='WT.mobile' content='15925603485'><META name='WT.brand' content='gotone'>\n" +
            " </head>\n" +
            "<body>\n" +
            "<div class=\"main\">\n" +
            "<div><img alt=\"\" width=\"780px\" height=\"187px\" style=\"background: no-repeat left top;\" src=\"/getRemoteImage.do?url=ImgeUrl_0\"></div>\n" +
            "  <div class=\"center\">\n" +
            "    <div class=\"div1\">\n" +
            "      <table class=\"tb1\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "        <tr>\n" +
            "          <th><strong>客　　户</strong>：</th>\n" +
            "          <td>林~</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th><strong>号　　码</strong>：</th>\n" +
            "          <td>159****3485</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th><strong>套餐名称</strong>：</th>\n" +
            "          <td>4G飞享套餐</td>\n" +
            "        </tr><tr>\n" +
            "  <th><strong>套餐描述</strong>：</th>\n" +
            "          <td>套餐内含:用户通过选择指定“飞享套餐基本模组”和“手机上网模组”（注1），个性化定义套餐内容。套餐含来电显示，享受国内被叫免费，超出后国内主叫长市漫享受一口价资费（注2）、上网流量0.29元/MB(不足1M精确到分)。 注1：“飞享套餐基本模组”已包含套餐月费，订购不同的“手机上网模组”、月费相应增加。套餐内含国内主叫时长及上网流量均不包括港澳台地区。注2：具体语音单价见语音模组描述，12586及12520类特殊业务除外。注3：虚拟网通话不享受套餐资费。   2015年10月起，套餐流量当月不清零，即本套餐内流量当月未用完，可结转至次月使用，次月底清零。如变更套餐，该套餐本月剩余流量无法结转至下月使用。。</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th><strong>客户星级</strong>：</th>\n" +
            "          <td>二星级</td>\n" +
            "        </tr>        <tr>\n" +
            "          <th><strong>计费周期</strong>：</th>\n" +
            "          <td>2019年09月01日至2019年09月30日</td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "      <table width=\"221\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tb2\">\n" +
            "        <tr>\n" +
            "          <th width=\"154\"><b>扣除本月费用后充值账户余额</b></th>\n" +
            "          <td width=\"67\" align=\"left\" class=\"money\">35.43 元</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "        <tr>\n" +
            "          <th><b>本月费用总额</b></th>\n" +
            "          <td align=\"left\" class=\"money\">63.00 元</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th>其中：个人实际消费</th>\n" +
            "          <td align=\"left\" class=\"money\">63.00 元</td>\n" +
            "        </tr>\n" +
            "      <tr><td align='left' colspan='2' class='money'><input type='button' value='即时充值' onclick='window.open(\"http://service.zj.10086.cn/cz/\")'/></td></tr></table>\n" +
            "    </div>\n" +
            "    <div class=\"div3\"><span>费用和账户信息</span><br />\n" +
            "    <table cellspacing=\"0\" cellpadding=\"0\" class=\"tb3\">\n" +
            "         <tr>\n" +
            "          <td align=\"left\" valign=\"top\"><table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tb3\">\n" +
            "            <tr>\n" +
            "              <td colspan=\"2\" class=\"totalTd\" align=\"center\">个人费用信息</td>\n" +
            "            </tr>\n" +
            "            <tr>\n" +
            "              <th class=\"fBlack\" align=\"left\"><strong>费用项目</strong></th>\n" +
            "              <td class=\"fBlack\"  align=\"center\"><strong>金额/元</strong></td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">套餐及固定费</th>\n" +
            "              <td align=\"center\">126.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">语音通信费</th>\n" +
            "              <td align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">短彩信费</th>\n" +
            "              <td align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">上网费</th>\n" +
            "              <td align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">增值业务费</th>\n" +
            "              <td align=\"center\">5.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">代收费</th>\n" +
            "              <td align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">补收费</th>\n" +
            "              <td align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">优惠费</th>\n" +
            "              <td align=\"center\">68.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" align=\"left\">第三方支付</th>\n" +
            "              <td align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\">\n" +
            "              <th class=\"fBlack\" align=\"left\"><strong>合计</strong></th>\n" +
            "              <td class=\"fBlack\" align=\"center\">63.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\">\n" +
            "              <th class=\"fBlack\" align=\"left\"><strong>他人（单位）代付</strong></th>\n" +
            "              <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\">\n" +
            "              <th class=\"fBlack\" align=\"left\"><strong>第三方支付</strong></th>\n" +
            "              <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\">\n" +
            "              <th class=\" fBlack\" align=\"left\"><strong>个人实际消费</strong></th>\n" +
            "              <td class=\"fBlack\" align=\"center\" colspan=\"2\">63.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\"></tr>\n" +
            "          </table></td>\n" +
            "        <td>\n" +
            "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"f_l\">\n" +
            "        <tr>\n" +
            "          <td width=\"14\">&nbsp;</td>\n" +
            "          <td  class=\"middlepic\"><h3>近6个月消费图</h3>\n" +
            "            <img src=\"/getRemoteImage.do?url=ImgeUrl_1\" alt=\"消费分析图\" width=\"169\" height=\"115\" />\n" +
            "            <h3>优惠后当月费用结构图</h3>\n" +
            "            <img src=\"/getRemoteImage.do?url=ImgeUrl_2\" alt=\"当月费用结构图\" width=\"165\" height=\"148\" />\n" +
            "</td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "        </td>\n" +
            "        </tr> \n" +
            "          </table>\n" +
            "    <div class=\"div8\"><span>账户信息</span>\n" +
            "           <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tb5\">\n" +
            "            <tr class=\"totalTd\">\n" +
            "              <th align=\"center\" rowspan=\"2\" class=\"fBlack\"><strong>费用项目</strong></th>\n" +
            "              <td height=\"46\" rowspan=\"2\" align=\"center\" class=\"fBlack\"><strong>08月底充值账户余额<br /></strong></td>\n" +
            "              <td align=\"center\"  rowspan=\"2\" class=\"fBlack\"><b>账户欠费</b></td>\n" +
            "              <td align=\"center\" colspan=\"4\" class=\"fBlack\">本月入账</td>\n" +
            "              <td align=\"center\"  rowspan=\"2\" class=\"fBlack\"><b>本月账户费用总额</b></td>\n" +
            "              <td align=\"center\"  rowspan=\"2\" class=\"fBlack\"><b>本月充值账户余额</b></td>\n" +
            "            </tr>\n" +
            "            <tr style=\"background:#89B929\">\n" +
            "              <td align=\"center\" class=\"fBlack\">充值缴费</td>\n" +
            "              <td align=\"center\" class=\"fBlack\">返还预存 </td>\n" +
            "              <td align=\"center\" class=\"fBlack\">返还赠送 </td>\n" +
            "              <td align=\"center\" class=\"fBlack\">其他</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th  align=\"center\"  class=\"fBlack\"><strong>金额/元</strong></th>\n" +
            "              <td class=\"fBlack\" align=\"center\">74.43</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">12.00</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">12.00</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">63.00</td>\n" +
            "              <td class=\"fBlack\" align=\"center\">35.43</td>\n" +
            "            </tr>\n" +
            "          </table>\n" +
            "    </div>\n" +
            "    </div>\n" +
            "    <div class=\"div3\"><br />\n" +
            "    <tr>\n" +
            "    <td>注：您上月为4G飞享套餐的家庭/群组成员，当前展示账户为账户所有人所属的账户。如您因业务变更发生了账户变化，可通过发送11至10086查询当前话费余额，或登录www.10086.cn网站查询。    </td>\n" +
            "    </tr>    </div>\n" +
            "    <div class=\"div4\"><span>本月末剩余可兑换积分：3433</span>\n" +
            "      <table cellspacing=\"0\" cellpadding=\"0\" class=\"tab_jf\">\n" +
            "  <tr>\n" +
            "    <th class=\"totalTd\">本月末剩余可兑换积分</th>\n" +
            "    <th>=</th>\n" +
            "    <th>本月消费积分</th>\n" +
            "    <th>+</th>\n" +
            "    <th>本月促销积分</th>\n" +
            "    <th>+</th>\n" +
            "    <th>上月末剩余可兑换积分</th>\n" +
            "    <th>-</th>\n" +
            "    <th>本月已兑换积分</tr>\n" +
            "  <tr>\n" +
            "    <td> 3433 </td>\n" +
            "    <td>= </td>\n" +
            "    <td>95</td>\n" +
            "    <td>+</td>\n" +
            "    <td>0</td>\n" +
            "    <td>+</td>\n" +
            "    <td>-4862</td>\n" +
            "    <td>-</td>\n" +
            "    <td>-8200</td>\n" +
            "    </tr>\n" +
            "\t   \t</table>&nbsp;&nbsp; 年底清零积分    1233\n" +
            "    </div>\n" +
            "\t <div class=\"div5\">\n" +
            "\t  <div class=\"divcell1\"><div  class=\"totalTd\">特别说明：</div><ul class=\"memocon\">\n" +
            "      <li class=\"num\">1、</li>      <li>上月充值账户余额是指扣除上月费用后的充值账户余额，如余额不足，则为账户欠费。</li>\n" +
            "      <li class=\"num\">2、</li>\n" +
            "      <li>本月入账金额中充值缴费、返还预存费用和返还赠送费用均指本号码发生的金额，家庭业务成员或同账户下其他用户充值的金额合并展现为其他入账。</li>\n" +
            "      <li class=\"num\">3、</li>\n" +
            "      <li>套餐及固定费：指各类套餐包月费、语音、短信息或上网优惠包月费、基本月租费、停机保号费等。</li>\n" +
            "      <li class=\"num\">4、</li>\n" +
            "      <li>增值业务费包括自有业务和在个人客户侧计费的集团成员类产品的功能费、通信费等。</li>\n" +
            "      <li class=\"num\">5、</li>\n" +
            "      <li>代收费：是您使用各类SP（信息服务提供商）、声讯台等业务产生的信息费用，该费用由中国移动代收。</li>\n" +
            "      <li class=\"num\">6、</li>\n" +
            "      <li>本期新增积分仅当您结清本期费用后才生效,积分商城：jf.10086.cn</li>\n" +
            "    </ul>\n" +
            "   </div>  <div class=\"divcell2\"><div   class=\"totalTd\">消费计算方法：</div>\n" +
            "  <ul class=\"memocon\">\n" +
            "      <li class=\"num\">1、</li>\n" +
            "      <li>本月费用总额=个人实际消费+家庭套餐成员消费+群组消费+家庭统一支付成员消费+同账户下其他用户消费。</li>   \n" +
            "      <li class=\"num\">2、</li>\n" +
            "      <li>合计＝套餐及固定费＋语音通信费+上网费+短彩信费+增值业务费＋代收费＋补收费-优惠费</li>   \n" +
            "      <li class=\"num\">3、</li>\n" +
            "      <li>他人（单位）代付=各项由单位或他人代付的费用总额。例如：单位统一支付个人的虚拟网月费。</li>   \n" +
            "      <li class=\"num\">4、</li>\n" +
            "      <li>个人实际消费=合计-他人（单位）代付。</li>\n" +
            "     <br />\n" +
            "    </ul>\n" +
            "  </div>\n" +
            "<div class=\"memo\">\n" +
            "感谢您使用中国移动客户账单，如您在账单使用中有任何疑问、意见或建议，欢迎您登录www.10086.cn的账务中心进行查询和留言。\n" +
            "  <br />\n" +
            "  您可在账单附录页中查询本月的各项金额入账明细、各科目消费明细和套餐内资源使用明细情况。 <br />\n" +
            "您可登录www.10086.cn的账务中心定制电子账单、查询历史账单、历史详单、实时扣费、订购关系、积分消费明细、手机支付消费明细信息以及获得其他帮助信息。\n" +
            "<br />\n" +
            "您可发送KTZD至10086定制电子账单。</div>\n" +
            "    </div>\n" +
            "    <div class=\"div6\">\n" +
            "<div class=\"div6_icon\"><img src=\"/getRemoteImage.do?url=ImgeUrl_3\" alt=\"\" id=\"img2\" /><img src=\"/getRemoteImage.do?url=ImgeUrl_4\" alt=\"\"  id=\"img1\" /></div>\n" +
            "      </div>\n" +
            "  </div>\n" +
            "  <div><img alt=\"\" width=\"780px\" height=\"75px\" style=\"background: no-repeat left top;\" src=\"/getRemoteImage.do?url=ImgeUrl_5\"></div>\n" +
            "  <div class=\"center\" >\n" +
            "    <div class=\"div7\">      <div><br />\n" +
            "<span>一、个人费用明细</span></div>\n" +
            "<table cellspacing=\"0\" cellpadding=\"0\" class=\"tb3\" >\n" +
            "        <tr>\n" +
            "          <td class=\"totalTd\"  align=\"center\" width=\"150\">费用项目</td>\n" +
            "          <td class=\"totalTd\"  align=\"center\" width=\"67\">金额/元</td>\n" +
            "          <td class=\"centerline\"align=\"center\"></td>\n" +
            "          <td class=\"totalTd\"  align=\"center\" width=\"150\">费用信息</td>\n" +
            "          <td class=\"totalTd\"  align=\"center\"  width=\"67\">金额/元</td>\n" +
            "          <td width=\"10\" class=\"centerline\">&nbsp;</td>\n" +
            "          <td class=\"totalTd\" align=\"center\" width=\"150\">费用信息</td>\n" +
            "          <td class=\"totalTd\" align=\"center\"  width=\"67\">金额/元</td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td width=\"217\" valign=\"top\" colspan=\"2\">\n" +
            "\t\t\t   <table width=\"217\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tb3\">\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>套餐及固定费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">126.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th align=\"left\" class=\"fBlack\">含 校园流量包月费</th>\n" +
            "              <td align=\"center\">50.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th align=\"left\" class=\"\">移动数据流量套餐月费</th>\n" +
            "              <td align=\"center\">50.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th align=\"left\" class=\"\">飞享套餐基本模组月费</th>\n" +
            "              <td align=\"center\">18.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th align=\"left\" class=\"\">多终端共享功能费</th>\n" +
            "              <td align=\"center\">8.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>语音通信费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>短彩信费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "          </table></td>\n" +
            "          <td align=\"center\" class=\"centerline\"></td>\n" +
            "          <td width=\"217\" valign=\"top\" colspan=\"2\">\n" +
            "\t\t\t   <table width=\"217\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tb3\">\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>上网费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>增值业务费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">5.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th align=\"left\" class=\"fBlack\">含 彩铃业务功能费</th>\n" +
            "              <td align=\"center\">5.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>代收费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "<tr class=\"gray\" >\n" +
            "<th align=\"left\" class=\"padding18\">&nbsp;</th>\n" +
            "<td align=\"left\">&nbsp;</td>\n" +
            " </tr>\n" +
            "<tr class=\"\" >\n" +
            "<th align=\"left\" class=\"padding18\">&nbsp;</th>\n" +
            "<td align=\"left\">&nbsp;</td>\n" +
            " </tr>\n" +
            "<tr class=\"gray\" >\n" +
            "<th align=\"left\" class=\"padding18\">&nbsp;</th>\n" +
            "<td align=\"left\">&nbsp;</td>\n" +
            " </tr>\n" +
            "          </table></td>\n" +
            "          <td align=\"center\" class=\"centerline\"></td>\n" +
            "          <td width=\"217\" valign=\"top\" colspan=\"2\">\n" +
            "\t\t\t   <table width=\"217\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" class=\"tb3\">\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>补收费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>优惠费</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">68.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th align=\"left\" class=\"fBlack\">含 校园50GB流量包月费促销-48个月</th>\n" +
            "              <td align=\"center\">50.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th align=\"left\" class=\"\">校园-飞享套餐语音包月费减免10元优惠</th>\n" +
            "              <td align=\"center\">10.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"gray\" >\n" +
            "              <th align=\"left\" class=\"\">流量分享（首个成员月费减免）促销</th>\n" +
            "              <td align=\"center\">8.00</td>\n" +
            "            </tr>\n" +
            "            <tr class=\"\" >\n" +
            "              <th class=\"fBlack\" width=\"150\" align=\"left\"><strong>第三方支付</strong></th>\n" +
            "              <td class=\"fBlack\" width=\"67\" align=\"center\">0.00</td>\n" +
            "            </tr>\n" +
            "<tr class=\"gray\" >\n" +
            "<th align=\"left\" class=\"padding18\">&nbsp;</th>\n" +
            "<td align=\"left\">&nbsp;</td>\n" +
            " </tr>\n" +
            "          </table></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <td height=\"13\" align=\"left\" colspan=\"2\"></td>\n" +
            "          <td align=\"left\" class=\"centerline\"></td>\n" +
            "          <td align=\"left\" colspan=\"2\"></td>\n" +
            "          <td class=\"centerline\"></td>\n" +
            "          <td colspan=\"2\" align=\"left\" ></td>\n" +
            "        </tr>\n" +
            "        <tr>\n" +
            "          <th align=\"left\" class=\"totalTd\" colspan=\"9\">&nbsp;合计：63.00元&nbsp;&nbsp;&nbsp; 他人（单位）代付 0.00元&nbsp;&nbsp;&nbsp; 第三方支付 0.00元&nbsp;&nbsp;&nbsp; 个人实际消费 63.00元</th>\n" +
            "        </tr>\n" +
            "       </table>\n" +
            "\t\t<table class=\"tb5\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "        <tr>\n" +
            "          <td height=\"20\" colspan=\"3\" >&nbsp;</td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "\t\t<div class=\"div8\"><span>二、通信量使用信息明细</span>\n" +
            "\t\t<table class=\"tb5\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "        <tr>\n" +
            "          <td width=\"204\" class=\"totalTd\" style=\" text-align:center\">优惠套餐(产品)</td>\n" +
            "          <td width=\"189\" class=\"totalTd\" style=\" text-align:center\">免费项目</td>\n" +
            "          <td width=\"156\" class=\"totalTd\">免费总量</td>\n" +
            "          <td width=\"121\" class=\"totalTd\">实际享受量</td>\n" +
            "        </tr>\n" +
            "        \n" +
            "        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">手机上网自选50元包(享8GB)</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">咪咕爱看国内流量</td>\n" +
            "          <td class=\"fBlack\">30720MB</td>\n" +
            "          <td class=\"fBlack\"></td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">手机上网自选50元包(享8GB)</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">国内数据流量</td>\n" +
            "          <td class=\"fBlack\">8192MB</td>\n" +
            "          <td class=\"fBlack\"></td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">VPMN个人产品763</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">虚拟网内本地被叫时长</td>\n" +
            "          <td class=\"fBlack\">277777小时46分39秒</td>\n" +
            "          <td class=\"fBlack\"></td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">动感特权礼包(全省校区语音)</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">高校范围内主叫国内语音</td>\n" +
            "          <td class=\"fBlack\">1小时40分</td>\n" +
            "          <td class=\"fBlack\">4分</td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">校园50GB流量包</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">指定区域数据流量</td>\n" +
            "          <td class=\"fBlack\">51200MB</td>\n" +
            "          <td class=\"fBlack\">1279MB 908KB</td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">手机上网自选50元包(享8GB)</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">国内数据流量-结转</td>\n" +
            "          <td class=\"fBlack\">8192MB</td>\n" +
            "          <td class=\"fBlack\">1206MB 697KB</td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">飞享套餐18元基本模组</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">国内语音主叫时长</td>\n" +
            "          <td class=\"fBlack\">50分</td>\n" +
            "          <td class=\"fBlack\"></td>\n" +
            "        </tr>        <tr class=\"gray\">\n" +
            "          <td class=\"fBlack\" align=\"centre\">校园语音优惠包</td>\n" +
            "          <td class=\"fBlack\" align=\"center\">高校范围内主叫国内网内语音分钟数1000分钟</td>\n" +
            "          <td class=\"fBlack\">16小时40分</td>\n" +
            "          <td class=\"fBlack\"></td>\n" +
            "        </tr>           <tr>\n" +
            "\t\t\t\t<table class=\"tb5\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n" +
            "        \t\t\t<tr>\n" +
            "          \t\t\t\t<td style=\" text-align:center\" colspan=\"3\" class=\"totalTd\">共享流量明细</td>\n" +
            "          \t\t\t</tr>\n" +
            "        \t\t\t<tr>\n" +
            "          \t\t\t\t<td width=\"244\" style=\" text-align:center\" class=\"totalTd\">共享流量所有人</td>\n" +
            "          \t\t\t\t<td width=\"249\" style=\" text-align:center\" class=\"totalTd\">使用人</td>\n" +
            "          \t\t\t\t<td width=\"156\" style=\" text-align:center\" class=\"totalTd\">使用量</td>\n" +
            "          \t\t\t</tr>\n" +
            "        \t\t\t<tr class=\"gray\">\n" +
            "          \t\t\t\t<td style=\" text-align:center;\" class=\"fBlack\">本机</td>\n" +
            "          \t\t\t\t<td style=\" text-align:center;\" class=\"fBlack\">15068191581</td>\n" +
            "          \t\t\t\t<td style=\" text-align:center;\" class=\"fBlack\"></td>\n" +
            "          \t\t\t</tr>\t\t\t\t</table>           </tr>\n" +
            "        </table>\n" +
            "<div class=\"fBlack\">计量单位规则：时长为**小时**分**秒；费用为**.**元；流量为*MB*KB(1MB=1024KB)；短彩信为**条</div></div>\n" +
            "<span>三、本月入账明细</span>\n" +
            "      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tb8\">\n" +
            "        <tr>\n" +
            "          <td width=\"86\" align=\"center\" valign=\"top\" class=\"totalTd\">入账时间</td>\n" +
            "          <td width=\"160\" align=\"center\" valign=\"top\" class=\"totalTd\">入账方式</td>\n" +
            "          <td width=\"64\" align=\"center\" valign=\"top\" class=\"totalTd\">本金金额</td>\n" +
            "          <td width=\"71\" align=\"center\" valign=\"top\" class=\"totalTd\">馈赠金金额</td>\n" +
            "          <td width=\"133\" valign=\"top\" align=\"center\" class=\"totalTd\">优惠活动</td>\n" +
            "          <td width=\"154\" align=\"center\"  class=\"totalTd\">备注</td>\n" +
            "        </tr>\n" +
            "        <tr class=\"gray\">\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">2019-09-17</td>\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">预存返还</td>\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">12.00 元</td>\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">0.00 元</td>\n" +
            "          <td valign=\"top\">2019话费1加1预存300送300话费（分月返充）-保底58（24个月）</td>\n" +
            "          <td align=\"center\" class=\"gray\">剩余19个月结束</td>\n" +
            "        </tr>\n" +
            "        <tr class=\"gray\">\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">2019-09-17</td>\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">预存返还</td>\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">0.00 元</td>\n" +
            "          <td align=\"center\" valign=\"top\" class=\"gray\">12.00 元</td>\n" +
            "          <td valign=\"top\">2019话费1加1预存300送300话费（分月返充）-保底58（24个月）</td>\n" +
            "          <td align=\"center\" class=\"gray\">剩余19个月结束</td>\n" +
            "        </tr>\n" +
            "      </table>\n" +
            "    </div>\n" +
            "    <div class=\"div10\" >\n" +
            "      <i>更加美好的通信生活，我们携手创造</i>\n" +
            "    <div class=\"clear\"></div></div>\n" +
            "  </div>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>\n" +
            " \n" +
            "\n" +
            "<div align=\"center\" style=\"color:red;\"><p>尊敬的客户，您尚未开通邮箱账单服务，可点击此链接开通邮箱账单服务（<a href='http://service.zj.10086.cn/yw/query/queryBillState.do?bid=BD399F39E6DB48CFE044001635842131'>链接到网账单服务开通页面</a>）</p><br/></div>";


    private static String html2 = "<div class=\"div3\">\n" +
            " <span>费用和账户信息</span>\n" +
            " <br>\n" +
            " <table cellspacing=\"0\" cellpadding=\"0\" class=\"tb5\">\n" +
            "  <tbody>\n" +
            "   <tr>\n" +
            "    <td colspan=\"2\" class=\"totalTd\" align=\"center\">费用信息</td>\n" +
            "   </tr>\n" +
            "   <tr>\n" +
            "    <th class=\"fBlack\" align=\"left\"><strong>费用项目</strong></th>\n" +
            "    <td class=\"fBlack\" align=\"center\"><strong>金额/元</strong></td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\">\n" +
            "    <th class=\"fBlack\" align=\"left\">套餐及固定费</th>\n" +
            "    <td align=\"center\">9.40</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"\">\n" +
            "    <th class=\"fBlack\" align=\"left\">语音通信费</th>\n" +
            "    <td align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\">\n" +
            "    <th class=\"fBlack\" align=\"left\">短彩信费</th>\n" +
            "    <td align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"\">\n" +
            "    <th class=\"fBlack\" align=\"left\">上网费</th>\n" +
            "    <td align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\">\n" +
            "    <th class=\"fBlack\" align=\"left\">增值业务费</th>\n" +
            "    <td align=\"center\">1.15</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"\">\n" +
            "    <th class=\"fBlack\" align=\"left\">代收费</th>\n" +
            "    <td align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\">\n" +
            "    <th class=\"fBlack\" align=\"left\">补收费</th>\n" +
            "    <td align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"\">\n" +
            "    <th class=\"fBlack\" align=\"left\">优惠费</th>\n" +
            "    <td align=\"center\">8.05</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\">\n" +
            "    <th class=\"fBlack\" align=\"left\"><strong>合计</strong></th>\n" +
            "    <td class=\"fBlack\" align=\"center\">2.50</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"\">\n" +
            "    <th class=\"fBlack\" align=\"left\"><strong>他人（单位）代付</strong></th>\n" +
            "    <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\">\n" +
            "    <th class=\"fBlack\" align=\"left\"><strong>其中第三方支付</strong></th>\n" +
            "    <td class=\"fBlack\" align=\"center\">0.00</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"\">\n" +
            "    <th class=\" fBlack\" align=\"left\"><strong>个人实际消费</strong></th>\n" +
            "    <td class=\"fBlack\" align=\"center\">2.50</td>\n" +
            "   </tr>\n" +
            "   <tr class=\"gray\"></tr>\n" +
            "  </tbody>\n" +
            " </table>\n" +
            "</div>";
    public static void main(String[] args) {
//        Pattern pattern = Pattern.compile("[0-9]{1,4}\\.[0-9]{2}");
//        Matcher matcher = pattern.matcher(html2);
//        if(matcher.find()){
//            System.out.println(matcher.group());
//        }
        Document doc = Jsoup.parse(html2);
        Elements div3 = doc.select(".div3");
        if (CollectionUtils.isEmpty(div3)) {
            return;
        }
        Elements tb31 = div3.select("th");
        if (CollectionUtils.isEmpty(tb31)) {
            return;
        }
        for (Element element : tb31) {
            if(element.toString().contains("套餐及固定费")){
                String value = ((Element)element.parentNode()).select("td").text();
                System.out.println("baseFee:"+value);
            }

            if(element.toString().contains("增值业务费")){
                String value = ((Element)element.parentNode()).select("td").text();
                System.out.println("extraFee:"+value);
            }

            if(element.toString().contains("个人实际消费")){
                String value = ((Element)element.parentNode()).select("td").text();
                System.out.println("totalFee:"+value);
            }
        }

//        Elements tds = tb31.select("td");
//        if (CollectionUtils.isEmpty(tds)) {
//            return;
//        }
//        Elements tb32 = tds.select(".tb3");
//        if (CollectionUtils.isEmpty(tb32)) {
//            return;
//        }
//
//        Elements trs = tb32.select("tr");
//        for (int i = 0; i < trs.size() - 1; i++) {
//            if (0 == i) {
//                continue;
//            }
//            Element tr = trs.get(i);
//
//            String key = getTextByTrIndexAndThIndex(tr, 0);
//            String value = getTextByTrIndexAndTdIndex(tr, 0);
//
//            if (StringUtils.equals("套餐及固定费", key)) {
//                System.out.println("套餐及固定费：" + value);
//            }
//
//            if (StringUtils.equals("增值业务费", key)) {
//                System.out.println("增值业务费：" + value);
//            }
//
//            if (StringUtils.equals("个人实际消费", key)) {
//                System.out.println("个人实际消费：" + value);
//            }
//
//        }


    }


    public static String getTextByTrIndexAndThIndex(Element tr, int thIndex) {
        Elements ths = tr.select("th");

        if (ths.isEmpty()) {
            return null;
        }

        Element th = ths.get(thIndex);
        return th.text().trim().replaceAll(Jsoup.parse("&nbsp;").text(), "").replaceAll("<br>", "").trim();
    }

    public static String getTextByTrIndexAndTdIndex(Element tr, int tdIndex) {
        Elements tds = tr.select("td");

        if (((Elements) tds).isEmpty()) {
            return null;
        }

        Element td = ((Elements) tds).get(tdIndex);

        return td.text().trim().replaceAll(Jsoup.parse("&nbsp;").text(), "").replaceAll("<br>", "").trim();
    }
}
