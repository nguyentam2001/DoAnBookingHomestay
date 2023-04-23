package nvt.doan.dto;

import lombok.Data;

import java.util.List;

@Data
public class DashboardDTO {
    List<CardDTO> cards;
    List<ChartDTO> charts;
}
