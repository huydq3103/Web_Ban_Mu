<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Biểu đồ thống kê</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
            integrity="sha384-rOA1PnstxnOBLzCLMcre8ybwbTmemjzdNlILg8O7z1lUkLXozs4DHonlDtnE7fpc"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/bc8223d7dd.js" crossorigin="anonymous"></script>
</head>
<body>

<%@include file="../Layout/header.jsp" %>
<div class="container">
    <h1 class="mt-4">Biểu đồ thống kê</h1>
    <div class="mb-3">
        <label for="datepicker" class="form-label">Ngày:</label>
        <input type="date" id="datepicker" class="form-control" onchange="updateChart()">
    </div>
    <div class="mb-3">
        <label for="weekpicker" class="form-label">Tuần:</label>
        <input type="week" id="weekpicker" class="form-control" onchange="updateChart()">
    </div>
    <div class="mb-3">
        <label for="monthpicker" class="form-label">Tháng:</label>
        <input type="month" id="monthpicker" class="form-control" onchange="updateChart()">
    </div>
    <div class="mb-3">
        <label for="inventorySelect" class="form-label">Sản phẩm tồn:</label>
        <select id="inventorySelect" class="form-select" onchange="updateChart()">
            <option value="inStock" selected>Hàng bán chạy</option>
            <option value="outOfStock">Hàng tồn</option>
        </select>
    </div>
    <div class="mb-4">
        <canvas id="myChart"></canvas>
    </div>
</div>

<script>
    // Hàm cập nhật biểu đồ khi ngày, tuần, tháng hoặc tùy chọn sản phẩm tồn thay đổi
    function updateChart() {
        // Lấy giá trị từ các trường input
        var selectedDate = document.getElementById("datepicker").value;
        var selectedWeek = document.getElementById("weekpicker").value;
        var selectedMonth = document.getElementById("monthpicker").value;
        var selectedInventory = document.getElementById("inventorySelect").value;

        // Tạo URL với các tham số truy vấn
        var url = '/bieu-do/chart-data' +
            '?date=' + encodeURIComponent(selectedDate) +
            '&week=' + encodeURIComponent(selectedWeek) +
            '&month=' + encodeURIComponent(selectedMonth) +
            '&inventory=' + encodeURIComponent(selectedInventory);

        // Gửi yêu cầu GET đến máy chủ
        fetch(url)
            .then(response => response.json())
            .then(data => {
                // Lấy dữ liệu từ phản hồi JSON
                var labels = [];
                var values = [];

                if (data.listNgay) {
                    labels = data.listNgay.map(item => item[1]);
                    values = data.listNgay.map(item => item[2]);
                } else if (data.listTuan) {
                    labels = data.listTuan.map(item => item[1]);
                    values = data.listTuan.map(item => item[2]);
                } else if (data.listThang) {
                    labels = data.listThang.map(item => item[1]);
                    values = data.listThang.map(item => item[2]);
                }
                // Kiểm tra nếu là hàng tồn thì giá trị của tất cả các cột đều là "full"
                if (selectedInventory === 'true') {
                    values = Array(labels.length).fill("full");
                }

                // Cập nhật dữ liệu biểu đồ
                myChart.data.labels = labels;
                myChart.data.datasets[0].data = values;
                myChart.update();
            })
            .catch(error => {
                console.error('Lỗi:', error);
            });
    }

    // Dữ liệu mẫu ban đầu cho biểu đồ
    var initialData = {
        labels: ['Tháng 1', 'Tháng 2', 'Tháng 3', 'Tháng 4', 'Tháng 5'],
        values: [100, 85, 70, 65, 60]
    };

    // Tạo biểu đồ ban đầu với dữ liệu mẫu
    var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: initialData.labels,
            datasets: [{
                label: 'Số lượng bán',
                data: initialData.values,
                backgroundColor: 'rgba(54, 162, 235, 0.5)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>
</html>
