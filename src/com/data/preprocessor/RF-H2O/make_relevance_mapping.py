#!/usr/bin/python

import os

#with open('stats_try') as f:
with open('/home/kuldeep/git/cs267/src/com/data/preprocessor/RF-H2O/test-data-group-stats') as f:
    lines = f.read().splitlines()

#print lines

#with open('pred_try) as f:
with open('/home/kuldeep/git/cs267/src/com/data/preprocessor/RF-H2O/output_scores_pred.txt') as f:
    lines_pred = f.read().splitlines()


def process_pred_chunk(rec, start):
	#print start
	cnt = int(rec[1])
	(c4, c3, c2, c1, c0) = rec[2:]
	#print cnt, c4, c3, c2, c1, c0
	
	k = 1

	f1 = open("temp_stats", "w")
	  
	for i in range(start, start + cnt):
		#print lines_pred[i]
		f1.write(str(k) + ":" + lines_pred[i] + "\n")
		k  = k + 1


	f1.close()

	#print os.system("sort -t \":\" -k 2 -n -r temp_stats > temp_stats_sort")
	os.system("sort -t \":\" -k 2 -n -r temp_stats > temp_stats_sort")

	with open('temp_stats_sort') as f3:
    		set2 = f3.read().splitlines()	

	f2 = open("inter2", "w")

	s1 = 0
	k3 = 0 
	rel = 4
	for k1 in (rec[2:]):
		#print s1, k3

		#print "start of chunk is ", s1, int(k1) + s1
		for k2 in range(s1, int(k1) + s1):
			#print s1, k1	
			#print set2[k2].split(":")[0] + " => " + set2[k2].split(":")[1],rel
			f2.write(set2[k2].split(":")[0] + ":" + str(rel) + "\n")
			k3 = k3 + 1
		
		s1 = s1 + k3
		k3 = 0
		rel = rel - 1
	
	f2.close()
	os.system("sort -t \":\" -k 1 -n inter2 > temp_stats_sort_new")
	os.system("cat temp_stats_sort_new >> final_map_test")
	return cnt


i = 0
cnt1 = 0
for r in lines:
	#print i, " = ", r
	i = i + 1
	rec = r.split(":")
	#print "chunk rec =", rec
	cnt1 = process_pred_chunk(rec,cnt1)
	#print rec[1]
	#for k in range(0, int(rec[1])):	
		#print k




