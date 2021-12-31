package com.y1ph.easy.design.common.utils;

import com.y1ph.easy.design.common.BaseTree;

import java.io.Serializable;
import java.util.*;

/**
 * 树形工具类
 *
 * @author WFT
 * @since 2022/1/1
 */
public class TreeUtil {

    /**
     * 私有化构造函数
     */
    private TreeUtil() {

    }

    /**
     * 以静态内部类实例化当前对象,从而达到懒汉式单例的效果
     */
    private static class Holder {
        private final static TreeUtil INSTANCE = new TreeUtil();
    }

    /**
     * 获取当前实例
     *
     * @return {@link TreeUtil}
     */
    public static TreeUtil getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 构建树形列表
     *
     * @param list   数据列表
     * @param rootId 根节点
     * @param <Id>   {@link Serializable}
     * @param <T>    {@link BaseTree}
     * @return {@link List} 树形结构的列表
     */
    public <Id extends Serializable, T extends BaseTree<Id, T>> List<T> build(List<T> list, Id rootId) {
        //  排序
        list.sort(Comparator.comparing(BaseTree::getSort));
        //  以Id作为Key，将列表存入Map集合中，方便后续查找
        Map<Id, T> map = new HashMap<>(list.size());
        list.forEach(item -> map.put(item.getId(), item));
        //  构建返回值列表
        List<T> resultList = new ArrayList<>();
        list.forEach(item -> {
            //  判断是否为跟节点
            if (item.getPid().equals(rootId)) {
                resultList.add(item);
            } else {
                //  获取父节点
                T parent = map.get(item.getPid());
                if (null != parent) {
                    //  获取父类节点的子节点列表
                    List<T> childList = parent.getChildren();
                    if (null == childList) {
                        parent.setChildren(childList = new ArrayList<>());
                    }
                    childList.add(item);
                }
            }
        });
        return resultList;
    }

}
