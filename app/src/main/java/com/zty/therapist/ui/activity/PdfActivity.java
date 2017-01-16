package com.zty.therapist.ui.activity;

import android.widget.TextView;

import com.joanzapata.pdfview.PDFView;
import com.joanzapata.pdfview.listener.OnPageChangeListener;
import com.zty.therapist.R;
import com.zty.therapist.base.BaseActivity;
import com.zty.therapist.utils.DownloadUtils;

import java.io.File;

import butterknife.BindView;

/**
 * Created by zty on 2017/1/6.
 */

public class PdfActivity extends BaseActivity implements OnPageChangeListener {
    @BindView(R.id.pdfView)
    PDFView pdfView;
    @BindView(R.id.textPdfPage)
    TextView textPdfPage;

    String pdfName;

    @Override
    protected int getContentView() {
        return R.layout.view_pdf;
    }

    @Override
    protected void initData() {
        pdfName = getIntent().getStringExtra("pdfName");
        title.setText(pdfName);
        display();
    }

    private void display() {
        File file = new File(DownloadUtils.file + DownloadUtils.assent, pdfName + ".pdf");
        pdfView.fromFile(file)
                //                .pages(0, 0, 0, 0, 0, 0) // 默认全部显示，pages属性可以过滤性显示
                .defaultPage(1)//默认展示第一页
                .onPageChange(this)//监听页面切换
                .load();
    }

    @Override
    public void onFailureCallback(int requestCode, String errorMsg) {

    }

    @Override
    public void onSuccessCallback(int requestCode, String response) {

    }

    @Override
    public void onPageChanged(int page, int pageCount) {
        textPdfPage.setText(page + "/" + pageCount);
    }
}
