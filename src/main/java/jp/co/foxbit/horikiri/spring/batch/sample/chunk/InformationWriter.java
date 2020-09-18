package jp.co.foxbit.horikiri.spring.batch.sample.chunk;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import jp.co.foxbit.horikiri.spring.batch.sample.model.Information;

public class InformationWriter implements ItemWriter<Information> {

    @Override
    public void write(List<? extends Information> information) throws Exception {

        information.forEach(i -> System.out.println(String.format("WriteÅF%s", i.getUserID())));
    }

}
