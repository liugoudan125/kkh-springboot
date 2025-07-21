package com.seeseesea.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.model.SysFileDTO;
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
     * @param files         文件
     * @param uploadUserId 上传用户ID
     * @return 文件信息
     */
    List<SysFileDTO> uploadFiles(List<MultipartFile> files, String uploadUserId);

    /**
     * 分页查询文件列表
     *
     * @param request 查询请求参数
     * @param current 当前页码
     * @param size    每页大小
     * @return 分页文件信息
     */
    Page<SysFileDTO> getFilePageList(SysFileRequest request, Long current, Long size);

    /**
     * 根据文件分类分页查询
     *
     * @param request      查询请求参数
     * @param fileCategory 文件分类 (image/video/audio/document)
     * @param current      当前页码
     * @param size         每页大小
     * @return 分页文件信息
     */
    Page<SysFileDTO> getFilePageListByCategory(SysFileRequest request, String fileCategory, Long current, Long size);

    /**
     * 根据文件扩展名分页查询
     *
     * @param request   查询请求参数
     * @param extension 文件扩展名
     * @param current   当前页码
     * @param size      每页大小
     * @return 分页文件信息
     */
    Page<SysFileDTO> getFilePageListByExtension(SysFileRequest request, String extension, Long current, Long size);

    /**
     * 根据ID查询文件详情
     *
     * @param id 文件ID
     * @return 文件详情
     */
    SysFileDTO getFileById(String id);


    /**
     * 删除文件
     *
     * @param id 文件ID
     * @return 是否删除成功
     */
    Boolean deleteFile(String id);
}
