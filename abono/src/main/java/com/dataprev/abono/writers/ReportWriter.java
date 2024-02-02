package com.dataprev.abono.writers;

import com.dataprev.abono.dtos.PagamentoReportDto;
import lombok.Getter;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileItemWriter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class ReportWriter<T> extends FlatFileItemWriter<T> {

    private int count = 0;
    private BigDecimal totalValue = BigDecimal.ZERO;


    @Override
    public void write(Chunk<? extends T> items) throws Exception {
        this.count = this.count + items.size();
        List<PagamentoReportDto> itemList = (List<PagamentoReportDto>) items.getItems();
        for (PagamentoReportDto item : itemList) {
            //this.totalValue = this.totalValue.add(item.getValorPagamento());
        }
        super.write(items);
    }


}
