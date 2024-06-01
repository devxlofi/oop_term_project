import java.awt.Color;
import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ColorDeserializer extends StdDeserializer<Color> {
    public ColorDeserializer() {
        super(Color.class);
    }

    @Override
    public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        int red = node.get("red").intValue();
        int green = node.get("green").intValue();
        int blue = node.get("blue").intValue();
        int alpha = node.get("alpha").intValue();
        return new Color(red, green, blue, alpha);
    }
}
