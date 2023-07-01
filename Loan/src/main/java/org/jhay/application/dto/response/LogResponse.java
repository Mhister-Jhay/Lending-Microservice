package org.jhay.application.dto.response;

import java.util.List;

public class LogResponse {
    private Long start_time;
    private Integer time_spent;
    private Integer attempts;
    private Integer errors;
    private boolean success;
    private boolean mobile;
    private String[] input;
    private List<HistoryResponse> history;
}
