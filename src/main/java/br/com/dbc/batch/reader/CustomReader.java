package br.com.dbc.batch.reader;

import br.com.dbc.model.Receita;
import br.com.dbc.model.ReceitaDTO;
import br.com.dbc.service.ReceitaService;
import br.com.dbc.service.impl.ReceitaServiceImpl;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

public class CustomReader implements ItemStreamReader<Receita> {
    private Object objAtual;
    private ItemStreamReader<Object> delegate;

    public CustomReader(ItemStreamReader<Object> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Receita read() throws Exception{
        if(objAtual == null)
            objAtual = delegate.read();

        ReceitaDTO dto = (ReceitaDTO) objAtual;
        objAtual = null;

        Receita receita = null;
        if(dto != null) {
             receita = Receita.builder()
                    .agencia(dto.getAgencia())
                    .conta(dto.getConta())
                    .saldo(Double.parseDouble(dto.getSaldo().replace(",", ".")))
                    .status(dto.getStatus())
                    .build();
        }

        return receita;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }
}
