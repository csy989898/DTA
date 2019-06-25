package com.example.administrator.maintenanceapp.Activity.Image;


import java.io.Serializable;


/**
 * 一个图片对象
 * 
 * @author Administrator
 */
public class ImageItem implements Serializable
{
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -3613750090880810573L;

    public String imageId;

    public String thumbnailPath;

    public String imagePath;

    public boolean isSelected = false;
}
