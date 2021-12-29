package com.aplan.grab.weibo;

import com.aplan.bean.model.HeatModel;
import lombok.SneakyThrows;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class WeiboRealtimeDemo {

    public static void main(String[] args) throws IOException {

        List<HeatModel> heatModels = weiboGrab();
        for (HeatModel heatModel : heatModels) {
            System.out.println(heatModel);
        }
    }


    @SneakyThrows
    public static List<HeatModel> weiboGrab() {
        String[] realtime = {"realtimehot", "entrank"};
        String base_url = "https://s.weibo.com";
        List<HeatModel> list = new ArrayList<>();

        for (int i = 0; i < realtime.length; i++) {
            String url = base_url + "/top/summary/summary?cate=" + realtime[i];

            Connection connection = Jsoup.connect(url);

            // 请求头
            Map<String, String> map = new HashMap<>();
            map.put("Cookie", "_s_tentry=passport.weibo.com; Apache=6380212503995.537.1640757656071; SINAGLOBAL=6380212503995.537.1640757656071; ULV=1640757656082:1:1:1:6380212503995.537.1640757656071:; SUB=_2A25Mz432DeRhGeBM6FsQ8ibIzjyIHXVvvPg-rDV8PUNbmtAKLWLskW9NRORjwiV9JgQgf-LS4KvqBGMDa0mi8vLk; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWUmSVpYroHYYRchFFk6epO5JpX5KzhUgL.FoqEe0.peonXSK52dJLoIp7LxKML1KBLBKnLxKqL1hnLBoMceoe4eKzRSh-7; ALF=1672294694; SSOLoginState=1640758694");
            connection.headers(map);

            // get 请求
            Document document = connection.get();
            Element body1 = document.body();
            Elements tbody = body1.getElementsByTag("tbody");
            for (Element element : tbody) {
                Elements tr = element.getElementsByTag("tr");
                for (Element element1 : tr) {
                    Elements td01 = element1.getElementsByClass("td-01");
                    Elements td02 = element1.getElementsByClass("td-02");
                    Elements td03 = element1.getElementsByClass("td-03");
                    String a = td02.tagName("a").text();
                    String href = td02.get(0).childNodes().get(1).attr("href");

                    String tag = td03.tagName("i").text();
                    int tag2 = 0;
                    String[] s = a.split(" ");
                    if (s.length >= 2) {
                        switch (tag) {
                            case "商":
                                tag2 = 1;
                                break;
                            case "新":
                                tag2 = 2;
                                break;
                            case "":
                                tag2 = 4;
                                break;
                            default:
                                tag2 = 0;
                        }

                        HeatModel heatModel = new HeatModel();
                        heatModel.setHeatNumber(s.length == 2 ? Long.parseLong(s[1]) : Long.parseLong(s[2]));
                        heatModel.setType(realtime[i]);
                        heatModel.setTitle(s[0]);
                        heatModel.setIndex(Integer.valueOf(td01.text()));
                        heatModel.setCreateTime(new Date());
                        heatModel.setPlatformType(1);
                        heatModel.setImg("");
                        heatModel.setTag(tag2);
                        heatModel.setUrl(base_url + href);
                        list.add(heatModel);
//                        System.out.println("排名：" + td01.text() + ",昵称：" + s[0] + ",热度：" + (s.length == 2 ? s[1] : s[2]));
                    }
                }
            }
        }
        return list;
    }

}
