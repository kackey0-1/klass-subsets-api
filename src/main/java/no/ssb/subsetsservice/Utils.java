package no.ssb.subsetsservice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {

    public static boolean isYearMonthDay(String date){
        return date.matches("([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))");
    }

    public static boolean isVersion(String version){
        return version.matches("(\\d\\.\\d\\.\\d)");
    }

    public static boolean isClean(String str){
        return str.matches("^[a-zA-Z0-9-_]+$");
    }

    public static JsonNode getSelfLinkObject(ObjectMapper mapper, ServletUriComponentsBuilder servletUriComponentsBuilder, JsonNode subset){
        String subsetVersion = subset.get("version").textValue();
        ObjectNode hrefNode = mapper.createObjectNode();
        hrefNode.put("href", servletUriComponentsBuilder.toUriString()+"/"+subsetVersion);
        ObjectNode self = mapper.createObjectNode();
        self.set("self", hrefNode);
        return self;
    }
}
