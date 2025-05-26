// 引入exios
import axios from "axios";
// 通过axios创建对象-request对象，用于发送请求到后端
const request = axios.create({
    timeout:50000
})

// request拦截器的处理：可以对请求做统一的处理（比如统一的加入Token，content-Type等）
request.interceptors.request.use(config => {
    // 如果Content-Type为multipart/form-data，则不需要设置Content-Type
    if(config.headers['Content-Type'] ==='multipart/form-data'){
        config.headers['token'] = localStorage.getItem('token')
        return config
    }
    config.headers['Content-Type'] = 'application/json;charset=utf-8'
    config.headers['token'] = localStorage.getItem('token')
    return config
},error => {return Promise.reject(error)})

// 导出
export default request

// response拦截器 可以在调用接口响应后，统一处理返回结果
request.interceptors.response.use(response => {
    let res = response.data
    // 如果返回的是文件,就返回
    if(response.config.responseType === 'blob'){
        return res
    }
    // 如果是String，就转为json对象
    if(typeof res === 'string'){
        // 如果res 不为null，就进行转换成json对象
        res = res ? JSON.parse(res):res;
    }
    return res
},error => {
    console.log("err",error)
    return Promise.reject(error);
})