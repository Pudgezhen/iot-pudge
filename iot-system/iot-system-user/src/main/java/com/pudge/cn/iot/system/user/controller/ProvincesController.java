package com.pudge.cn.iot.system.user.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pudge.cn.iot.api.user.entity.Provinces;
import com.pudge.cn.iot.common.response.R;
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
    public R<String> addOne(@RequestBody Provinces provinces){
        boolean save = provincesService.save(provinces);
        if (save){
            return R.success("新增成功");
        }else{
            return R.fail(R.FAIL_CODE,"新增失败");
        }
    }

    @DeleteMapping("/delete/{id}")
    public R<String> deleteOne(@PathVariable("id") String id){
        boolean flag = provincesService.removeById(id);
        if (flag){
            return R.success("删除成功");
        }else{
            return R.fail(R.FAIL_CODE,"删除失败");
        }
    }

    @PostMapping("/modify")
    public R<String> ModifyOne(@RequestBody Provinces provinces){
        boolean save = provincesService.updateById(provinces);
        if (save){
            return R.success("修改成功");
        }else{
            return R.fail(R.FAIL_CODE,"修改成功");
        }
    }

    @GetMapping("/queryOne/{id}")
    public R<Provinces> queryOne(@PathVariable("id") String id){
        Provinces provinces = provincesService.getById(id);
        if (provinces != null){
            return R.success(provinces);
        }else{
            return R.fail(R.FAIL_CODE,"无此记录");
        }
    }

    @GetMapping("/queryAll/{pageSize}/{pageNum}")
    public R<PageInfo<Provinces>> queryAll(@PathVariable("pageSize") Integer pageSize,
                                                   @PathVariable("pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo<Provinces> pageInfo = new PageInfo<>(provincesService.list());
        return R.success(pageInfo);
    }


}
