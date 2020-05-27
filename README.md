# treebuild
构建树结构，springboot2整合mybatis、mysql和FastJson

1、数据库
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '部门管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '总集团', 0, 0);
INSERT INTO `sys_dept` VALUES (2, 1, '北京分公司', 1, 0);
INSERT INTO `sys_dept` VALUES (3, 1, '上海分公司', 2, 0);
INSERT INTO `sys_dept` VALUES (4, 3, '技术部', 0, 0);
INSERT INTO `sys_dept` VALUES (5, 3, '销售部', 1, 0);
INSERT INTO `sys_dept` VALUES (6, 2, '其他部', 0, 0);
INSERT INTO `sys_dept` VALUES (7, 4, '技术三级菜单', 0, 0);
INSERT INTO `sys_dept` VALUES (8, 5, '销售三级菜单', 0, 0);

SET FOREIGN_KEY_CHECKS = 1;

2、springboot2整合FastJson
(1)添加依赖
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.15</version>
		</dependency>
(2)设置FastJson处理数据替换Spingboot默认的Jackson
@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter {

    /**
     * 使用fastJson的方式一     需要  extends WebMvcConfigurationSupport
     * 1.需要先定义一个convert转换消息的对象
     * 2.添加fastJson的配置信息，比如：是否要格式化返回的json数据
     * 3.在convert中添加配置信息
     * 4.将convert添加到converts当中
     * @param converters
     *  这种方式才能解决乱码问题，下面的方式二我试了一下，不得行
     *  原文链接：https://blog.csdn.net/qq_33371766/article/details/82220605
     */
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
        while(iterator.hasNext()){
            HttpMessageConverter<?> converter = iterator.next();
            if(converter instanceof MappingJackson2HttpMessageConverter){
                iterator.remove();
            }
        }
        super.configureMessageConverters(converters);
        //1.需要先定义一个convert转换消息对象
        FastJsonHttpMessageConverter fastConverter=new FastJsonHttpMessageConverter();
        //2.添加fastJson的配置信息，比如：是否要格式化返回的json数据
        FastJsonConfig fastConfig=new FastJsonConfig();
        fastConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //处理中文乱码问题(不然出现中文乱码)
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(fastMediaTypes);
        //3.在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastConfig);
        //4.将convert添加到converts当中
        converters.add(fastConverter);
    }
 }
 
 3、项目参考（后续补充）
树状构建参考： https://blog.csdn.net/qq_38164123/article/details/94358131

 
