with open('original_labels') as f:
#with open('/home/kuldeep/git/cs267/trials/lables_new') as f:
    lines1 = f.read().splitlines()


with open('final_rf_labels') as f:
#with open('/home/kuldeep/git/cs267/src/com/data/preprocessor/lables_old') as f:
    lines2 = f.read().splitlines()


print len(lines1), len(lines2)

den = len(lines1)

num = 0

for i in range(0,len(lines1)):
	if ( lines1[i] == lines2[i] ):
		num = num + 1



print num, den

print  (num/float(den))
