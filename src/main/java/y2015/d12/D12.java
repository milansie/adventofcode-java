package y2015.d12;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.AdventDay;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class D12 extends AdventDay {

    public static void main(String[] argv) {
        new D12().run();
    }

    @Override
    public String first(List<String> input) {

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(input.getFirst());
            return "" + sumNumbersFromJson(rootNode, false);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String second(List<String> input) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(input.getFirst());
            return "" + sumNumbersFromJson(rootNode, true);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    private int sumNumbersFromJson(JsonNode node, boolean ignoreRed) {

        if (node.isInt()) {
            return node.asInt();
        }

        int sum = 0;

        if (node.isArray()) {
            for (JsonNode arrayNode : node) {
                sum += sumNumbersFromJson(arrayNode, ignoreRed);
            }
            return sum;
        }

        Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
        while (fields.hasNext()) {

            Map.Entry<String, JsonNode> field = fields.next();

            if (ignoreRed && (field.getKey().equals("red") || field.getValue().asText().equals("red"))) {
                return 0;
            }

            sum += sumNumbersFromJson(field.getValue(), ignoreRed);

        }

        return sum;
    }

}

