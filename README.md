# Lab A10 – GridView, RecyclerView & ViewPager2

**Môn học:** Lập trình trên các thiết bị di động (INT4211)  
**Học kỳ / Tuần:** Tuần 10  
**Sinh viên thực hiện:** A10_241A010541  

---

## 📱 Giới thiệu ứng dụng (Album Ảnh)
Ứng dụng **Album Ảnh** được phát triển bằng **Java + XML (Android SDK)**, ứng dụng các thành phần hiện đại của Android Architecture:
- **RecyclerView**: Thay thế cho ListView/GridView truyền thống với cơ chế tái sử dụng `ViewHolder`, `GridLayoutManager` (cho phép đổi số cột 2, 3, 4 linh hoạt lúc chạy) và `StaggeredGridLayoutManager` (lưới so le kiểu Pinterest).
- **ViewPager2**: Thay thế cho widget Gallery cũ, cung cấp màn hình xem ảnh lớn với khả năng vuốt ngang mượt mà, đồng thời cập nhật số thứ tự trang (`1 / 15`).
- **Callback qua Interface**: Tách biệt hoàn toàn logic hiển thị (Adapter) và điều hướng (Activity), đảm bảo tuân thủ nguyên tắc thiết kế Android hiện đại.
- **Tính năng nâng cao (Phần 7)**:
  - **NC2**: Lưới so le `StaggeredGridLayoutManager` với chiều cao linh hoạt.
  - **NC4**: Thêm nút xóa ảnh trực quan, sử dụng `notifyItemRemoved` để tạo hiệu ứng trượt mượt mà.

---

## 📂 Cấu trúc thư mục dự án
```text
A10_241A010541/
├── app/
│   ├── src/main/
│   │   ├── java/vn/edu/vhu/ltdd/a10gallery/
│   │   │   ├── MainActivity.java      # Màn hình chính (RecyclerView, đổi cột, xóa ảnh)
│   │   │   ├── ViewerActivity.java    # Màn hình xem ảnh lớn (ViewPager2)
│   │   │   ├── AnhAdapter.java        # Adapter cho lưới ảnh (có callback & delete)
│   │   │   └── AnhPagerAdapter.java   # Adapter cho ViewPager2
│   │   ├── res/
│   │   │   ├── drawable/              # 15 ảnh chân dung hoạt hình (anh1.png - anh15.png)
│   │   │   ├── layout/                # activity_main, item_anh, activity_viewer, page_anh
│   │   │   └── values/                # strings.xml, themes.xml, colors.xml
│   │   └── AndroidManifest.xml        # Khai báo Activities
│   └── build.gradle.kts               # Dependencies (RecyclerView, ViewPager2, Material)
├── gradle/                            # Gradle Wrapper
└── build.gradle.kts
```

---

## 🚀 Hướng dẫn kiểm thử (Kịch bản Lab)
1. **Mở app**: Hiển thị lưới ảnh dạng so le / 3 cột với 15 ảnh chân dung hoạt hình sắc nét.
2. **Đổi số cột**: Bấm nút *"Đổi số cột"* để thay đổi số cột hoặc chuyển đổi LayoutManager.
3. **Xem ảnh lớn**: Chạm vào bất kỳ ảnh nào để mở [ViewerActivity](class://vn.edu.vhu.ltdd.a10gallery.ViewerActivity), vuốt ngang để chuyển ảnh và quan sát bộ đếm (VD: `5 / 15`).
4. **Xóa ảnh (NC4)**: Bấm nút `✕` trên mỗi ô ảnh để xóa ảnh với hiệu ứng `notifyItemRemoved`.
