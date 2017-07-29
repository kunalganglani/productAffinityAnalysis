package com.hackathon;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class JsonDateDeserializer extends JsonDeserializer<Date>
{
    @Override
    public Date deserialize(JsonParser jsonparser,
            DeserializationContext deserializationcontext) throws IOException, JsonProcessingException {

        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");
        String date = jsonparser.getText();
        if(date == null || date.equals("")) return null;
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}