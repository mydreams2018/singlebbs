package cn.kungreat.singlebbs.mapper;

public interface ManagerMapper {
    /*
    * 查询需要审核的主贴数量
    */
    Integer selectAuthPorts();

    /*
     * 查询需要审核的回贴数量
     */
    Integer selectAuthAnswerPorts();
}
