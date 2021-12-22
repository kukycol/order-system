package com.aplan.exception.login;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class CustomOAuthExceptionSerializer extends StdSerializer<CustomOAuth2Exception> {

    private static final long serialVersionUID = 1478842053473472921L;

    public CustomOAuthExceptionSerializer() {
        super(CustomOAuth2Exception.class);
    }

    /**
     * 自定义 Oauth 异常
     * username/password/grant_type
     * @param value
     * @param gen
     * @param provider
     * @throws IOException
     */
    @Override
    public void serialize(CustomOAuth2Exception value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeStartObject();
        gen.writeStringField("code", String.valueOf(value.getHttpErrorCode()));
        gen.writeStringField("msg", value.getMessage());
        gen.writeEndObject();
    }
}
