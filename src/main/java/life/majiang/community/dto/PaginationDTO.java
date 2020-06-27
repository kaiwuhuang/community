package life.majiang.community.dto;

import lombok.Data;

import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages=new ArrayList<>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if(page<1)
            page=1;
        if(page>totalPage)
            page=totalPage;
        this.page=page;
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }


        //是否展示第一页
        showFirstPage= page != 1;
        //是否展示最后一页
        showEndPage= !Objects.equals(page, totalPage);
        //是否展示上一页图标
        showPrevious=page!=1;
        //是否展示写一页图标
        showNext=!Objects.equals(page,totalPage);
    }
}
