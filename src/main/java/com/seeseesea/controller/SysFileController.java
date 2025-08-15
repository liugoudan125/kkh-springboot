package com.seeseesea.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.seeseesea.core.response.BaseResponse;
import com.seeseesea.model.dto.SysFileDTO;
import com.seeseesea.model.request.SysFileRequest;
import com.seeseesea.service.SysFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
     * @return 分页文件列表
     */
    @GetMapping("/page")
    public BaseResponse<IPage<SysFileDTO>> page(
            SysFileRequest request) {
        IPage<SysFileDTO> pageResult = sysFileService.page(request);
        return BaseResponse.ok(pageResult);
    }


    /**
     * 根据ID查询文件详情
     *
     * @param id 文件ID
     * @return 文件详情
     */
    @GetMapping("/{id}")
    public BaseResponse<SysFileDTO> getFileById(@PathVariable("id") Long id) {
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
