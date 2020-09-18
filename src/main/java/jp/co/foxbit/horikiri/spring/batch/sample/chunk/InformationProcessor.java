package jp.co.foxbit.horikiri.spring.batch.sample.chunk;

import org.springframework.batch.item.ItemProcessor;

import jp.co.foxbit.horikiri.spring.batch.sample.model.Information;

public class InformationProcessor implements ItemProcessor<Information, Information> {

    private static final String CONVERTED_PREFIX = "CONVERTED";
    @Override
    public Information process(Information informatin) throws Exception {

        System.out.println(String.format("ProcessÅF%s", informatin.getUserID()));

        return new Information(String.format("%s %s", CONVERTED_PREFIX, informatin.getUserID()),
                String.format("%s %s", CONVERTED_PREFIX, informatin.getFirstName()),
                String.format("%s %s", CONVERTED_PREFIX, informatin.getLastName()),
                String.format("%s %s", CONVERTED_PREFIX, informatin.getCreatedAt()),
                String.format("%s %s", CONVERTED_PREFIX, informatin.getCreatedAt()),
                informatin.getDeleted());
    }
    
}
