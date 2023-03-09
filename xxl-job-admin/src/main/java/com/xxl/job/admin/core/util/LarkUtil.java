package com.xxl.job.admin.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * customize appender for logback
 * message appender to fei shu
 */
@Component
public class LarkUtil {

    public void send(String url, String msg) {
        Text text = new Text();
        Elements elements = new Elements();
        elements.setText(text);
        text.setContent(CharSequenceUtil.format("<at id=\"all\"></at> {}", msg));
        JsonRootBean jsonRootBean = new JsonRootBean();
        jsonRootBean.setElements(CollUtil.toList(elements));
        Card card = new Card();
        card.setCard(jsonRootBean);
        HttpRequest.post(url)
                .header("Content-Type", "application/json")
                .body(JSONUtil.toJsonStr(card)).timeout(1000).execute().body();
    }


    public class Card {
        String msg_type = "interactive";
        JsonRootBean card;

        public String getMsg_type() {
            return msg_type;
        }

        public void setMsg_type(String msg_type) {
            this.msg_type = msg_type;
        }

        public JsonRootBean getCard() {
            return card;
        }

        public void setCard(JsonRootBean card) {
            this.card = card;
        }
    }


    public class JsonRootBean {
        private Config config = new Config();
        private List<Elements> elements;
        private Header header = new Header();

        public Config getConfig() {
            return config;
        }

        public void setConfig(Config config) {
            this.config = config;
        }

        public List<Elements> getElements() {
            return elements;
        }

        public void setElements(List<Elements> elements) {
            this.elements = elements;
        }

        public Header getHeader() {
            return header;
        }

        public void setHeader(Header header) {
            this.header = header;
        }
    }

    public class Header {
        private String template = "blue";
        private Title title = new Title();

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public Title getTitle() {
            return title;
        }

        public void setTitle(Title title) {
            this.title = title;
        }
    }


    public class Title {
        private String content = "XXLJOB";
        private String tag = "text";

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }


    public class Config {
        private boolean wide_screen_mode = true;

        public boolean isWide_screen_mode() {
            return wide_screen_mode;
        }

        public void setWide_screen_mode(boolean wide_screen_mode) {
            this.wide_screen_mode = wide_screen_mode;
        }
    }

    public class Elements {
        private String tag = "div";
        private Text text;

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public Text getText() {
            return text;
        }

        public void setText(Text text) {
            this.text = text;
        }
    }

    public class Text {
        private String content;
        private String tag = "lark_md";

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }
    }
}
