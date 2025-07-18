package com.seeseesea.core.utils;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

/**
 * 文件工具类
 * 包含常用的文件操作工具方法
 */
@UtilityClass
public class FileUtils {

    /**
     * 文件大小单位
     */
    private static final String[] FILE_SIZE_UNITS = {"B", "KB", "MB", "GB", "TB", "PB"};

    /**
     * 常见图片文件扩展名
     */
    private static final List<String> IMAGE_EXTENSIONS = Arrays.asList(
            "jpg", "jpeg", "png", "gif", "bmp", "webp", "ico", "svg"
    );

    /**
     * 常见视频文件扩展名
     */
    private static final List<String> VIDEO_EXTENSIONS = Arrays.asList(
            "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm", "m4v"
    );

    /**
     * 常见音频文件扩展名
     */
    private static final List<String> AUDIO_EXTENSIONS = Arrays.asList(
            "mp3", "wav", "flac", "aac", "ogg", "wma", "m4a"
    );

    /**
     * 常见文档文件扩展名
     */
    private static final List<String> DOCUMENT_EXTENSIONS = Arrays.asList(
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "rtf", "json", "md"
    );

    /**
     * 将文件大小（字节）转换为友好显示格式
     *
     * @param sizeInBytes 文件大小（字节）
     * @return 友好显示的文件大小字符串
     */
    public static String formatFileSize(long sizeInBytes) {
        if (sizeInBytes <= 0) {
            return "0 B";
        }

        int digitGroups = (int) (Math.log10(sizeInBytes) / Math.log10(1024));
        digitGroups = Math.min(digitGroups, FILE_SIZE_UNITS.length - 1);

        DecimalFormat df = new DecimalFormat("#,##0.#");
        return df.format(sizeInBytes / Math.pow(1024, digitGroups)) + " " + FILE_SIZE_UNITS[digitGroups];
    }

    /**
     * 获取文件扩展名（不包含点号）
     *
     * @param fileName 文件名
     * @return 文件扩展名，如果没有扩展名则返回空字符串
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == fileName.length() - 1) {
            return "";
        }

        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }

    /**
     * 获取不含扩展名的文件名
     *
     * @param fileName 完整文件名
     * @return 不含扩展名的文件名
     */
    public static String getFileNameWithoutExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return fileName;
        }

        return fileName.substring(0, lastDotIndex);
    }

    /**
     * 判断是否为图片文件
     *
     * @param fileName 文件名
     * @return 如果是图片文件返回 true，否则返回 false
     */
    public static boolean isImageFile(String fileName) {
        String extension = getFileExtension(fileName);
        return IMAGE_EXTENSIONS.contains(extension);
    }

    /**
     * 判断是否为视频文件
     *
     * @param fileName 文件名
     * @return 如果是视频文件返回 true，否则返回 false
     */
    public static boolean isVideoFile(String fileName) {
        String extension = getFileExtension(fileName);
        return VIDEO_EXTENSIONS.contains(extension);
    }

    /**
     * 判断是否为音频文件
     *
     * @param fileName 文件名
     * @return 如果是音频文件返回 true，否则返回 false
     */
    public static boolean isAudioFile(String fileName) {
        String extension = getFileExtension(fileName);
        return AUDIO_EXTENSIONS.contains(extension);
    }

    /**
     * 判断是否为文档文件
     *
     * @param fileName 文件名
     * @return 如果是文档文件返回 true，否则返回 false
     */
    public static boolean isDocumentFile(String fileName) {
        String extension = getFileExtension(fileName);
        return DOCUMENT_EXTENSIONS.contains(extension);
    }

    /**
     * 获取文件类型描述
     *
     * @param fileName 文件名
     * @return 文件类型描述
     */
    public static String getFileType(String fileName) {
        if (isImageFile(fileName)) {
            return "图片";
        } else if (isVideoFile(fileName)) {
            return "视频";
        } else if (isAudioFile(fileName)) {
            return "音频";
        } else if (isDocumentFile(fileName)) {
            return "文档";
        } else {
            return "其他";
        }
    }

    /**
     * 验证文件名是否合法（去除非法字符）
     *
     * @param fileName 原始文件名
     * @return 合法的文件名
     */
    public static String sanitizeFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "untitled";
        }

        // 移除或替换非法字符
        String sanitized = fileName.replaceAll("[\\\\/:*?\"<>|]", "_");

        // 移除首尾空格和点号
        sanitized = sanitized.trim().replaceAll("^[.]+|[.]+$", "");

        // 如果文件名为空，使用默认名称
        if (sanitized.isEmpty()) {
            return "untitled";
        }

        return sanitized;
    }

    /**
     * 生成唯一的文件名（如果文件已存在，在文件名后添加数字）
     *
     * @param directory 目录路径
     * @param fileName  原始文件名
     * @return 唯一的文件名
     */
    public static String generateUniqueFileName(String directory, String fileName) {
        File file = new File(directory, fileName);
        if (!file.exists()) {
            return fileName;
        }

        String nameWithoutExt = getFileNameWithoutExtension(fileName);
        String extension = getFileExtension(fileName);
        String extensionWithDot = extension.isEmpty() ? "" : "." + extension;

        int counter = 1;
        while (file.exists()) {
            String newFileName = nameWithoutExt + "(" + counter + ")" + extensionWithDot;
            file = new File(directory, newFileName);
            counter++;
        }

        return file.getName();
    }

    /**
     * 检查文件扩展名是否在允许的列表中
     *
     * @param fileName          文件名
     * @param allowedExtensions 允许的扩展名列表
     * @return 如果文件扩展名在允许列表中返回 true，否则返回 false
     */
    public static boolean isAllowedExtension(String fileName, List<String> allowedExtensions) {
        if (allowedExtensions == null || allowedExtensions.isEmpty()) {
            return true;
        }

        String extension = getFileExtension(fileName);
        return allowedExtensions.contains(extension);
    }

    /**
     * 获取文件的 MIME 类型
     *
     * @param fileName 文件名
     * @return MIME 类型
     */
    public static String getMimeType(String fileName) {
        String extension = getFileExtension(fileName);

        // 图片类型
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "bmp":
                return "image/bmp";
            case "webp":
                return "image/webp";
            case "svg":
                return "image/svg+xml";
            case "ico":
                return "image/x-icon";

            // 视频类型
            case "mp4":
                return "video/mp4";
            case "avi":
                return "video/x-msvideo";
            case "mov":
                return "video/quicktime";
            case "wmv":
                return "video/x-ms-wmv";
            case "flv":
                return "video/x-flv";
            case "mkv":
                return "video/x-matroska";
            case "webm":
                return "video/webm";

            // 音频类型
            case "mp3":
                return "audio/mpeg";
            case "wav":
                return "audio/wav";
            case "flac":
                return "audio/flac";
            case "aac":
                return "audio/aac";
            case "ogg":
                return "audio/ogg";
            case "wma":
                return "audio/x-ms-wma";

            // 文档类型
            case "pdf":
                return "application/pdf";
            case "doc":
                return "application/msword";
            case "docx":
                return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
            case "xls":
                return "application/vnd.ms-excel";
            case "xlsx":
                return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case "ppt":
                return "application/vnd.ms-powerpoint";
            case "pptx":
                return "application/vnd.openxmlformats-officedocument.presentationml.presentation";
            case "txt":
                return "text/plain";
            case "rtf":
                return "application/rtf";

            // 其他常见类型
            case "json":
                return "application/json";
            case "xml":
                return "application/xml";
            case "html":
            case "htm":
                return "text/html";
            case "css":
                return "text/css";
            case "js":
                return "application/javascript";
            case "zip":
                return "application/zip";
            case "rar":
                return "application/x-rar-compressed";
            case "7z":
                return "application/x-7z-compressed";

            default:
                return "application/octet-stream";
        }
    }
}