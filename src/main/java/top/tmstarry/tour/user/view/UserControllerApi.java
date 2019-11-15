package top.tmstarry.tour.user.view;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import top.tmstarry.tour.user.service.bean.User;

import javax.servlet.http.HttpServletRequest;

/**
 * 有关API的注解比较多，如果直接写在Controller中，会导致Controller臃肿，可以将API相关的内容放在接口中
 *有关user的请求
 *<p>
 *
 *注意：MVC中有关参数的注解需要加入，否则MVC和Swagger都会出现问题。
 *
 *登录和注册
 */
@Api(value = "UserController",tags = {"用户操作API"})
public interface UserControllerApi  {

    /*参数需要加入注解：@RequestBody表示请求的数据是json格式，通过请求体传入，否则需要使用@ApiImplicitParam*/
    /*PackResponse表示响应使用的实体*/
    @ApiOperation(value = "创建User", notes = "创建", response = User.class)
    public User add(@ApiParam(name = "user", value = "json格式", required = true)User user, HttpServletRequest request);

    @ApiOperation(value = "删除User",notes = "删除")
    /*注意，如果要通过明确说明参数，则@ApiParam的name要和参数名称一致，否则Swagger的测试工具用不了*/
    public User delete(@ApiParam(name = "id", value = "整型Id", required = true) @PathVariable("id") int id);

    @ApiOperation(value = "更新User",notes = "更新")
    public User update(@ApiParam(name = "user", value = "json格式，必须有id", required = true) @RequestBody User user, @PathVariable("id") int id, HttpServletRequest request);

    /*@ApiOperation(value = "部分User更新", notes = "部分更新")
    *//*参数也可以不描述，会自动生成*//*
    public User patch(@RequestBody User user, @PathVariable("id") int id, HttpServletRequest request);*/


    @ApiOperation(value = "单个User查询", notes = "单个查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "整型Id", required = true, dataType = "int", paramType = "path")
    })
    public User get(@PathVariable("id") int id);


    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "模糊查询：用户名", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "查询：用户Id", required = false, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "order", value = "排序：排序条件", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "desc", value = "排序：有值为逆序，不填为升序", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "分页：第几页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页：每页多少行", required = true, dataType = "int", paramType = "query")
    })
    @ApiOperation(value = "按条件查询User", notes = "按条件查询")
    public Page<User> find(@ApiIgnore @RequestParam Map<String, String> map);*/
}
