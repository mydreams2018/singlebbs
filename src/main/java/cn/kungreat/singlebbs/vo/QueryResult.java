package cn.kungreat.singlebbs.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class QueryResult {
    private Object page;
    private List datas;
}