# GridPhoto九宫格图片选择和展示View
## 添加依赖
implementation 'com.github.AndroidLMY:GridPhoto:1.0.3'
## 使用方法
### 选择图片的GridSelectPhotoView
```
<com.lmy.gridphotolibrary.view.GridSelectPhotoView
            android:id="@+id/grid_select"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:maxNumber="3" />
```
maxNumber最大选择数量
设置图片数据
```
grid_select.setAddPhoto(GridSelectBean(photo.path, false))
```

### 显示多个图片的GridShowPhotoView

```
  <com.lmy.gridphotolibrary.view.GridShowPhotoView
        android:id="@+id/grid_show_view"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

```
设置图片数据
```
  grid_show_view.setDataList(mutableListOf<GridSelectBean>()）                         
```




 
    




