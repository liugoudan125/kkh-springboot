package com.seeseesea.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.seeseesea.model.dto.SysFileDTO;
import com.seeseesea.model.request.SysFileRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * (SysFile)业务层接口
 *
 * @author liuchenglong
 * @since 2025-07-02 22:25:41
 */
public interface SysFileService {

    /**
     * 上传文件
     *
     * @param files 文件
     * @return 文件信息
     */
    List<SysFileDTO> uploadFiles(List<MultipartFile> files);

    /**
     * 分页查询文件列表
     *
     * @param request 查询请求参数
     * @return 分页文件信息
     */
    IPage<SysFileDTO> page(SysFileRequest request);


    /**
     * 根据ID查询文件详情
     *
     * @param id 文件ID
     * @return 文件详情
     */
    SysFileDTO getFileById(Long id);


    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 是否删除成功
     */
    Boolean deleteFile(Long id);
}
