package com.pudge.cn.iot.system.user.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pudge.cn.iot.api.user.entity.Provinces;
import com.pudge.cn.iot.common.golbalResponse.PudResult;
import com.pudge.cn.iot.common.golbalResponse.ResultCode;
import com.pudge.cn.iot.system.user.service.IProvincesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 省份信息表 前端控制器
 * </p>
 *
 * @author pudge
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/provinces")
public class ProvincesController {

    @Autowired
    private IProvincesService provincesService;

    @PostMapping("/add")
    public PudResult<String> addOne(@RequestBody Provinces provinces){
        boolean save = provincesService.save(provinces);
        if (save){
            return PudResult.success(null,"新增成功");
        }else{
            return PudResult.failed(ResultCode.FAILED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public PudResult<String> deleteOne(@PathVariable("id") String id){
        boolean flag = provincesService.removeById(id);
        if (flag){
            return PudResult.success(null,"删除成功");
        }else{
            return PudResult.failed(ResultCode.FAILED);
        }
    }

    @PostMapping("/modify")
    public PudResult<String> ModifyOne(@RequestBody Provinces provinces){
        boolean save = provincesService.updateById(provinces);
        if (save){
            return PudResult.success(null,"修改成功");
        }else{
            return PudResult.failed(ResultCode.FAILED);
        }
    }

    @GetMapping("/queryOne/{id}")
    public PudResult<Provinces> queryOne(@PathVariable("id") String id){
        Provinces provinces = provincesService.getById(id);
        if (provinces != null){
            return PudResult.success(provinces);
        }else{
            return PudResult.failed("无此记录");
        }
    }

    @GetMapping("/queryAll/{pageSize}/{pageNum}")
    public PudResult<PageInfo<Provinces>> queryAll(@PathVariable("pageSize") Integer pageSize,
                                                   @PathVariable("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Provinces> pageInfo = new PageInfo<>(provincesService.list());
        return PudResult.success(pageInfo);
    }


}
