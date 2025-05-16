package io.datavines.runner.utils;

import io.datavines.common.utils.StringUtils;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 从datavinens中解析出时间变量，并将变量替换为顺序占位符
 * 提供反向渲染的功能
 * @author chenyuanbo
 * @DATE: 2025/5/16
 */
public class CustomPrepareUtil {


    // 返回替换结果和占位符内容
    @Getter
    public static class ReplacementResult {
        private final String replacedStr;  // 替换后的字符串
        private final List<String> placeholders;  // 原始占位符内容列表

        public ReplacementResult(String replacedStr, List<String> placeholders) {
            this.replacedStr = replacedStr;
            this.placeholders = placeholders;
        }

    }

    /**
     * 替换原始占位符为带顺序的占位符
     * @param input 原始字符串
     * @return ReplacementResult，包含替换后的字符串和占位符列表
     */
    public static ReplacementResult replacePlaceholders(String input) {
        List<String> placeholders = new ArrayList<>();
        // 正则匹配 ${...} 或 $[...]
        Pattern pattern = Pattern.compile("\\$\\{([^}]*)}|\\$\\[([^\\]]*)\\]");
        Matcher matcher = pattern.matcher(input);
        StringBuffer sb = new StringBuffer();
        int counter = 1;  // 占位符序号

        while (matcher.find()) {
            // 提取占位符内容
            String content = matcher.group(1) != null ? matcher.group(1) : matcher.group(2);
            placeholders.add(content);

            // 生成替换后的占位符（保留原始符号类型）
            String replacement;
            if (matcher.group(1) != null) {
                replacement = "${" + counter + "}";  // 保留 ${} 格式
            } else {
                replacement = "$[" + counter + "]"; // 保留 $[] 格式
            }
            counter++;

            // 替换匹配项并转义特殊字符
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);

        return new ReplacementResult(sb.toString(), placeholders);
    }

    /**
     * 恢复带顺序的占位符为原始内容
     * @param replacedStr 替换后的字符串
     * @param placeholders 原始占位符内容列表
     * @return 恢复后的字符串
     */
    public static String restorePlaceholders(String replacedStr, List<String> placeholders) {
        Pattern pattern = Pattern.compile("\\$\\{(\\d+)}|\\$\\[(\\d+)\\]");
        Matcher matcher = pattern.matcher(replacedStr);
        StringBuffer sb = new StringBuffer();

        while (matcher.find()) {
            int index;
            String originalPlaceholder;

            // 判断是 ${n} 还是 $[n]
            if (matcher.group(1) != null) {
                index = Integer.parseInt(matcher.group(1)) - 1;
//                originalPlaceholder = "${" + placeholders.get(index) + "}";
                originalPlaceholder = placeholders.get(index);
            } else {
                index = Integer.parseInt(matcher.group(2)) - 1;
//                originalPlaceholder = "$[" + placeholders.get(index) + "]";
                originalPlaceholder = placeholders.get(index);
            }

            // 替换回原始占位符
            matcher.appendReplacement(sb, Matcher.quoteReplacement(originalPlaceholder));
        }
        matcher.appendTail(sb);

        return sb.toString();
    }
    public static String restorePlaceholders(String replacedStr, String[] args) {
        if (args == null || args.length <= 2) {
            return replacedStr;
        }
        for (String item : args) {
            if (item.startsWith("quality=")) {
                String s = item.replaceFirst("quality=", "");
                if (StringUtils.isNotEmpty(s)) {
                    return restorePlaceholders(s, Arrays.asList(args));
                }
            }
        }
        return replacedStr;
    }


    // 测试示例
    public static void main(String[] args) {
        String original = "用户: ${name}, 时间: $[time], 地点: ${location}";

        // 替换为带序号的占位符
        ReplacementResult result = replacePlaceholders(original);
        System.out.println("[替换后字符串] " + result.getReplacedStr());
        // 输出：用户: ${1}, 时间: $[2], 地点: ${3}
        List<String> placeholders = result.getPlaceholders();
        System.out.println("[原始占位符] " + placeholders);
        // 输出：[name, time, location]

        // 恢复原始占位符
        AtomicInteger count = new AtomicInteger(1);
        List<String> collect = placeholders.stream().map(i -> String.valueOf(count.getAndIncrement())).collect(Collectors.toList());
        String restored = restorePlaceholders(result.getReplacedStr(), collect);
        System.out.println("[恢复后字符串] " + restored);
        // 输出：用户: ${name}, 时间: $[time], 地点: ${location}
    }

}
