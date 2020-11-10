package br.com.dbc.reader;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReaderConfigTest {

    @Autowired
    private FlatFileItemReader reader;

}
