import java.util.*;
import java.lang.*;

class Paypal
{
	int heap_size;
	Paypal(int i)
	{
		heap_size=i;
	}
	store[] build_heap(store[] A)
	{
		int i;
		for(i=heap_size/2;i>=1;i--)
			A=min_heapify(A,i);
		return A;
	}
	store[] min_heapify(store[] A,int i)
	{
		int l,r,smallest;
		store temp=new store(0,0);
		l=2*i;
		r=2*i+1;
		if(l<=heap_size && A[l].freq<A[i].freq)
			smallest=l;
		else
			smallest=i;
		if(r<=heap_size && A[r].freq<A[smallest].freq)
			smallest=r;
		if(smallest!=i)
		{
			temp.data=A[i].data;
			temp.freq=A[i].freq;
			
			A[i].data=A[smallest].data;
			A[i].freq=A[smallest].freq;
			
			A[smallest].data=temp.data;
			A[smallest].freq=temp.freq;
			
			A=min_heapify(A,smallest);
		}
		return A;
	}
	store[] heap_sort(store[] A)
	{
		store temp=new store(0,0);
		while(heap_size>1)
		{
			temp.data=A[1].data;
			temp.freq=A[1].freq;
			
			A[1].data=A[heap_size].data;
			A[1].freq=A[heap_size].freq;
			
			A[heap_size].data=temp.data;
			A[heap_size].freq=temp.freq;
			
			heap_size--;
			A=min_heapify(A,1);
		}
		return A;
	}
	public static void main(String args[])
	{
		int i,j;
		Scanner in=new Scanner(System.in);
		System.out.print("Enter no. of inputs: ");
		int n=in.nextInt();
		int[] a=new int[n+1];
		store[] A=new store[n+1];
		for(i=1;i<=n;i++)
		{
			System.out.print("Enter a["+i+"] : ");
			a[i]=in.nextInt();
		}
		int count=0;
		for(i=1;i<=n;i++)
		{
			count=0;
			for(j=1;j<=n;j++)
			{
				if(a[i]==a[j])
					count++;
			}
			A[i]=new store(a[i],count);
		}
		Paypal x=new Paypal(n);
		A=x.build_heap(A);
		A=x.heap_sort(A);
		for(i=1;i<=n;i++)
				a[i]=A[i].data;
		System.out.print("Sorted array: ");
		for(i=1;i<=n;i++)
			System.out.print(a[i]+" ");
	}
}
class store
{
	int data,freq;
	store(int data,int freq)
	{
		this.data=data;
		this.freq=freq;
	}
}