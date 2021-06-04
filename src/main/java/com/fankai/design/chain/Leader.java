package com.fankai.design.chain;

/**
 * 抽象处理角色
 */
public abstract class Leader {
    protected Leader leader;

    public Leader getLeader() {
        return leader;
    }

    public void setLeader(Leader leader) {
        this.leader = leader;
    }


}
