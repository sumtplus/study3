/**
 *  자바스크립트 결제 모듈
 */
console.log("payment module...");

var paymentService = (function() {
	return {
		// fno를 파라미터로 받아 요금제를 찾음
		feeFindBy: function(param, callback, error) {
			console.log("payment.feeFindBy()........");
			var url = param.cp + "/payment/getFee?fno=" + param.fno;
			console.log(url);
			//ajax호출
			$.getJSON(url, function(result) { //success
				if(callback) {
					callback(result);
				}
			}).fail(function(xhr, status, er) { // error
				if(error) {
					error(er);
				}
			});
		},
		
	};
})();










