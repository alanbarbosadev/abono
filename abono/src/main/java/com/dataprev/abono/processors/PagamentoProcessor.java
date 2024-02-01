package com.dataprev.abono.processors;

import com.dataprev.abono.entities.Pagamento;
import org.springframework.batch.item.ItemProcessor;

public class PagamentoProcessor implements ItemProcessor<Pagamento, Pagamento> {
    @Override
    public Pagamento process(Pagamento pagamento) throws Exception {
        return pagamento;
    }
}
