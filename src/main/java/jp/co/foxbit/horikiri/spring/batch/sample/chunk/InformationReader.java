package jp.co.foxbit.horikiri.spring.batch.sample.chunk;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import jp.co.foxbit.horikiri.spring.batch.sample.model.Information;

public class InformationReader implements ItemReader<Information>{

    private static final String USER_ID_DOMAIN = "@foxbit.co.jp";
    private static int counter = 0;

    @Override
    public Information read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (counter++ > 2) return null;

        var userID = String.format("%s%s", counter, USER_ID_DOMAIN);
        var firstName = String.format("%s%s", counter, counter);
        var lastName = String.valueOf(counter);
        var localTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(String.format("ReadÅF%s", userID));

        return new Information(userID, firstName, lastName, localTime, localTime, true);
    }

}
