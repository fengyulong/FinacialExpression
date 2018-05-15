$.extend($.fn.validatebox.defaults.rules, {  
    //移动手机号码验证  
    mobile: {//value值为文本框中的值  
        validator: function (value) {  
            var reg = /^1[3|4|5|8|9]\d{9}$/;  
            return reg.test(value);  
        },  
        message: '输入手机号码格式不准确.'  
    },  
    //国内邮编验证  
    zipcode: {  
        validator: function (value) {  
            var reg = /^[1-9]\d{5}$/;  
            return reg.test(value);  
        },  
        message: '邮编必须是非0开始的6位数字.'  
    },
    syscode:{
    	validator: function (value) {
    		var reg = /^[\w]+$/;
    		return reg.test(value);
    	},
    	message:'只能输入数字、字母、下划线'
    }
    
}); 