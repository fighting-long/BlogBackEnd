package my.lxh.blog.utils;

import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;
import java.util.*;


/**
 * @author lxh
 * @date 2020-12-25 14:14
 */
public class MarkdownUtil {

    /**
     * markdown格式转化为HTML格式
     * @param markdown
     * @return
     */
    public static String markdownToHtml(String markdown){
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(document);
    }

    /**
     * 增加扩展（标题锚点，表格生成）
     * Markdown转换成HTML
     * @param markdown
     * @return
     */
    public static String markdownToHtmlExtensions(String markdown){
        //h标题生成id
        Set<Extension> headingAnchorExtensions = Collections.singleton(HeadingAnchorExtension.create());
        //转化成table的HTML
        List<Extension> tableExtensions = Collections.singletonList(TablesExtension.create());
        Parser parser = Parser.builder().extensions(tableExtensions).build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().extensions(headingAnchorExtensions).extensions(tableExtensions)
                .attributeProviderFactory((attributeProviderContext)-> new CustomAttributeProvider()).build();
        return renderer.render(document);
    }

    /**
     * 处理标签的属性
     */
    static class CustomAttributeProvider implements AttributeProvider {
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            //改变a标签的target属性为_blank
            if(node instanceof Link){
                map.put("target","_blank");
            }
            if(node instanceof TableBlock){
                map.put("class","ui celled table");
            }
        }
    }

}
