package vn.edu.vnu.uet.nlp.examples;

import vn.edu.vnu.uet.nlp.uetnlp.UETnlp;

public class Demo {

	public static void main(String[] args) {
		String text_1 = "Nằm trên một vị trí khiêm tốn nhưng Song Long, thuộc sở hữu của bà Lan Khai Vo cùng người chị em Diep Lan Vo, hơn 30 năm nay vẫn thu hút nhiều thực khách nhờ những món ăn giữ nguyên hương vị ban đầu, theo NBC News. \"Chúng tôi quyết định nấu những món người Việt thường hay nhung nhớ, chế biến giống với cách mà mẹ và bà họ đã làm, những món họ không được ăn trong một thời gian dài\", bà Lan Khai nói. Nhà hàng này hồi năm 1981 vốn là một cửa hiệu bánh ngọt Pháp mang tên Song Long Bakery. Tuy nhiên công việc kinh doanh trì trệ đã khiến chị em bà Lan Khai phải ngừng hoạt động, chuyển sang hình thức mới vài năm sau đó.";
		String text_2 = "Theo Fox News, hình ảnh vệ tinh từ ImageSat International, chụp hôm 7/4 và được các quan chức quốc phòng Mỹ xác nhận là thực cho thấy hai chiến đấu cơ J-11 trên đảo Phú Lâm. Hình ảnh vệ tinh cũng cho thấy một hệ thống radar kiểm soát hoả lực mới lắp đặt trên đảo. Điều này giúp giàn phóng tên lửa đất đối không Trung Quốc triển khai hồi tháng hai đi vào hoạt động đầy đủ, kênh truyền hình này cho biết. Quân đội Mỹ quan ngại radar sẽ mới cho phép Trung Quốc theo dõi chiến đấu cơ, oanh tạc cơ và máy bay thu thập thông tin tình báo Mỹ đang giám sát quân đội Trung Quốc. Các hình ảnh từ ImageSat International cho thấy 4 trong số 8 tên lửa phòng không sẵn sàng khai hoả ở phía đông đảo Phú Lâm. J-11 bắt đầu đi vào phục vụ năm 1998. Chúng là phiên bản cải biến của máy bay Su-27 Nga, tương đương với máy bay F-15 của không quân Mỹ hoặc F/A-18 của hải quân Mỹ. Trung Quốc vài lần gần đây ngang nhiên triển khai J-11 lên đảo Phú Lâm. Fox News đưa tin về một lần triển khai hồi tháng hai, khi Ngoại trưởng Mỹ John Kerry tiếp đón người đồng cấp Trung Quốc ở Washington D.C. Tháng 11 năm ngoái, truyền thông nhà nước Trung Quốc cũng đăng ảnh J-11 trên đảo này. Các quan chức Mỹ và Đài Loan hồi tháng hai xác nhận Trung Quốc đã triển khai tên lửa đất đối không tới đảo Phú Lâm, thuộc quần đảo Hoàng Sa của Việt Nam, nhằm theo đuổi chiến lược quân sự hóa Biển Đông. Việt Nam đã gửi công hàm cho Đại sứ quán Trung Quốc để phản đối nước này đưa tên lửa đến đảo Phú Lâm, cho rằng đây là sự xâm phạm nghiêm trọng chủ quyền của Việt Nam đối với quần đảo Hoàng Sa, đe dọa hòa bình và ổn định trong khu vực cũng như an ninh, an toàn, tự do hàng hải và hàng không ở Biển Đông.";

		System.out.println(UETnlp.tagRawText(text_1));
		System.out.println(UETnlp.tagRawText(text_2));
	}

}
