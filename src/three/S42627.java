/*
    https://programmers.co.kr/learn/courses/30/lessons/42627
    디스크 컨트롤러
 */

package three;

import java.util.*;

class S42627 {
    public int solution(int[][] jobs) {
        DiskController dc = new DiskController();

        for (int[] job : jobs) {
            dc.addJob(job[0], job[1]);
        }
        dc.doAllJobs();
        return dc.getAveragetotalDelayTime();
    }
}

class DiskController {
    private int totalJobCount = 0;
    private int totalJobTime = 0;
    private int totalDelayTime = 0;
    private Queue<Job> remainJobs = new PriorityQueue<>((o1, o2) -> {
        if (o1.requestTime == o2.requestTime) {
            return ((Integer) o1.jobTime).compareTo(o2.jobTime);
        }
        return ((Integer) o1.requestTime).compareTo(o2.requestTime);
    });
    private Queue<Job> jobPool = new PriorityQueue<>(new Comparator<Job>() {
        @Override
        public int compare(Job o1, Job o2) {
            return ((Integer) o1.jobTime).compareTo(o2.jobTime);
        }
    });

    public void addJob(int requestTime, int jobTime) {
        Job j = new Job(requestTime, jobTime);
        remainJobs.add(j);
        totalJobCount++;
    }

    public void doAllJobs() {
        for (int i = 0; i < totalJobCount; i++) {
            doOneJob();
        }
    }

    private void doOneJob() {
        if (jobPool.isEmpty()) {
            Job j = remainJobs.poll();
            totalJobTime = j.requestTime + j.jobTime;
            totalDelayTime += j.jobTime;
        } else {
            Job j = jobPool.poll();
            totalJobTime += j.jobTime;
            totalDelayTime += (totalJobTime - j.requestTime);
        }
        addToJobPool();
    }

    private void addToJobPool() {
        while (remainJobs.size() > 0 && remainJobs.peek().requestTime <= totalJobTime) {
            jobPool.offer(remainJobs.poll());
        }
    }

    public int getAveragetotalDelayTime() {
        return totalDelayTime / totalJobCount;
    }

    static class Job {
        final int requestTime;
        final int jobTime;

        public Job(int requestTime, int jobTime) {
            this.requestTime = requestTime;
            this.jobTime = jobTime;

        }
    }
}
