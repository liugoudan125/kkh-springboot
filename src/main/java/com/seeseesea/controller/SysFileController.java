package com.seeseesea.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.core.utils.UserUtils;
import com.seeseesea.model.SysFileDTO;
import com.seeseesea.model.request.SysFileRequest;
import com.seeseesea.service.SysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * SysFileController
 */
@Slf4j
@RestController
@RequestMapping("sys_file")
@RequiredArgsConstructor
public class SysFileController {

    private final SysFileService sysFileService;

    /**
     * 批量上传文件接口
     *
     * @param files 文件
     * @return 文件信息
     */
    @PostMapping("/upload_batch")
    public BaseResponse<List<SysFileDTO>> uploadFile(
            @RequestParam("files") List<MultipartFile> files) {
        // 如果没有提供uploadUserId，使用默认值
        List<SysFileDTO> sysFiles = sysFileService.uploadFiles(files);
        return BaseResponse.ok(sysFiles);
    }

    /**
     * 分页查询文件列表
     *
     * @param request 查询条件
     * @param current 当前页码，默认第1页
     * @param size    每页大小，默认10条
     * @return 分页文件列表
     */
    @GetMapping("/page")
    public BaseResponse<Page<SysFileDTO>> getFilePageList(
            SysFileRequest request,
            @RequestParam(value = "current", defaultValue = "1") Long current,
            @RequestParam(value = "size", defaultValue = "10") Long size) {
        Page<SysFileDTO> pageResult = sysFileService.getFilePageList(request, current, size);
        return BaseResponse.ok(pageResult);
    }

    /**
     * 根据文件分类分页查询
     *
     * @param request      查询条件
     * @param fileCategory 文件分类 (image/video/audio/document)
     * @param current      当前页码，默认第1页
     * @param size         每页大小，默认10条
     * @return 分页文件列表
     */
    @GetMapping("/page/category")
    public BaseResponse<Page<SysFileDTO>> getFilePageListByCategory(
            SysFileRequest request,
            @RequestParam("fileCategory") String fileCategory,
            @RequestParam(value = "current", defaultValue = "1") Long current,
            @RequestParam(value = "size", defaultValue = "10") Long size) {
        Page<SysFileDTO> pageResult = sysFileService.getFilePageListByCategory(request, fileCategory, current, size);
        return BaseResponse.ok(pageResult);
    }

    /**
     * 根据文件扩展名分页查询
     *
     * @param request   查询条件
     * @param extension 文件扩展名 (如: jpg, pdf, mp4)
     * @param current   当前页码，默认第1页
     * @param size      每页大小，默认10条
     * @return 分页文件列表
     */
    @GetMapping("/page/extension")
    public BaseResponse<Page<SysFileDTO>> getFilePageListByExtension(
            SysFileRequest request,
            @RequestParam("extension") String extension,
            @RequestParam(value = "current", defaultValue = "1") Long current,
            @RequestParam(value = "size", defaultValue = "10") Long size) {
        Page<SysFileDTO> pageResult = sysFileService.getFilePageListByExtension(request, extension, current, size);
        return BaseResponse.ok(pageResult);
    }

    /**
     * 根据ID查询文件详情
     *
     * @param id 文件ID
     * @return 文件详情
     */
    @GetMapping("/{id}")
    public BaseResponse<SysFileDTO> getFileById(@PathVariable("id") String id) {
        SysFileDTO fileDetail = sysFileService.getFileById(id);
        return BaseResponse.ok(fileDetail);
    }


    /**
     * 删除文件
     *
     * @param request 请求参数，包含要删除的文件ID
     * @return 删除结果
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteFile(@RequestBody SysFileRequest request) {
        Boolean result = sysFileService.deleteFile(request.getId());
        return BaseResponse.ok(result);
    }
}
