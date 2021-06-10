package com.marlowe.music.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.marlowe.music.entity.Singer;
import com.marlowe.music.entity.Song;
import com.marlowe.music.mapper.SingerMapper;
import com.marlowe.music.service.ISingerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 歌手表 服务实现类
 * </p>
 *
 * @author marlowe
 * @since 2021-05-30
 */
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements ISingerService {


    @Autowired
    private SingerMapper singerMapper;


    @Override
    public PageInfo<Singer> allSinger(int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        List<Singer> singers = singerMapper.selectList(null);
        PageInfo<Singer> pageInfo = new PageInfo(singers);
        return pageInfo;
    }


    @Override
    public boolean addSinger(Singer singer) {
        return singerMapper.insert(singer) > 0;

    }

    /**
     * 通过id删除歌手
     *
     * @return
     */
    @Override
    public boolean deleteSinger(String id) {
        return singerMapper.deleteById(id) > 0;
    }

    /**
     * 批量删除歌手
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteSingers(List<Integer> ids) {
        return singerMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 通过主键id更新歌手信息
     *
     * @return
     */
    @Override
    public boolean updateSingerMsg(Singer singer) {
        return singerMapper.updateById(singer) > 0;
    }

    /**
     * 更新歌手头像
     *
     * @return
     */
    @Override
    public boolean updateSingerPic() {
        return false;
    }

    /**
     * 根据姓名查询歌手
     *
     * @return
     */
    @Override
    public PageInfo<Singer> findSingerByName(String name, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        PageInfo<Singer> pageInfo = new PageInfo(singers);
        return pageInfo;
    }

    /**
     * 根据性别查找歌手
     *
     * @return
     */
    @Override
    public PageInfo<Singer> findSingerBySex(int sex, int pageNo, int pageSize) {
        // 设置分页查询参数
        PageHelper.startPage(pageNo, pageSize);
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", sex);
        List<Singer> singers = singerMapper.selectList(queryWrapper);
        PageInfo<Singer> pageInfo = new PageInfo(singers);
        return pageInfo;
    }

    /**
     * 获得歌手总数量
     *
     * @return
     */
    @Override
    public int singerCount() {
        return singerMapper.selectCount(null);
    }

    /**
     * 根据性别获得歌曲数量
     *
     * @param sex
     * @return
     */
    @Override
    public int singerCountOfSex(int sex) {
        QueryWrapper<Singer> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sex", sex);
        Integer count = singerMapper.selectCount(queryWrapper);
        return count;
    }
}
