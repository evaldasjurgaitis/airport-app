import React, { useState } from 'react';
import axios from 'axios';

import { Button, Upload } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

import Loader from '../../../../shared/Loader/Loader';
import Row from '../../../../shared/Row/Row';
import Col from '../../../../shared/Col/Col';

import { JobStatus } from '../../../typing';

const RegionDataImport = () => {
    const [showRegionDataLoader, setShowRegionDataLoader] = useState(false);
    const [showRegionDataImportBtn, setShowRegionDataImportBtn] = useState(false);
    const [regionDataFilePath, setRegionDataFilePath] = useState('');
    const [showRegionDataImportStatusFailure, setShowRegionDataImportStatusFailure] = useState(false);
    const [showRegionDataImportStatusSuccess, setShowRegionDataImportStatusSuccess] = useState(false);

    const fileForm = (options: any) => {
        const { file } = options;
        const data = new FormData();
        data.append('file', file);
        return data;
    };

    const uploadRegionData = async (options: any) => {
        setShowRegionDataLoader(true);
        setShowRegionDataImportBtn(false);
        setShowRegionDataImportStatusFailure(false);
        setShowRegionDataImportStatusSuccess(false);
        await axios
            .post(`http://localhost:8082/api/files`, fileForm(options))
            .then((response) => {
                setShowRegionDataLoader(false);
                setShowRegionDataImportBtn(true);
                setRegionDataFilePath(response.data.path);
            })
            .catch(() => {
                setShowRegionDataLoader(false);
                setShowRegionDataImportStatusFailure(true);
            });
    };

    const pollingImportStatus = (jobId: number) => {
        axios
            .get(`http://localhost:8082/api/jobs/${jobId}/status`)
            .then((res) => {
                if (
                    (res.status === 200 && res.data.status === JobStatus.STARTED) ||
                    res.data.status === JobStatus.STARTING
                ) {
                    setTimeout(() => pollingImportStatus(jobId), 5000);
                } else {
                    if (res.data.status === JobStatus.COMPLETED) {
                        setShowRegionDataLoader(false);
                        setShowRegionDataImportStatusSuccess(true);
                    }
                    if (
                        !res ||
                        res.data.status === JobStatus.FAILED ||
                        res.data.status === JobStatus.STOPPING ||
                        res.data.status === JobStatus.STOPPED ||
                        res.data.status === JobStatus.ABANDONED
                    ) {
                        setShowRegionDataLoader(false);
                        setShowRegionDataImportStatusFailure(true);
                    }
                }
            })
            .catch(function () {
                setShowRegionDataLoader(false);
                setShowRegionDataImportStatusFailure(true);
            });
    };

    const executeImportData = async () => {
        setShowRegionDataImportBtn(false);
        setShowRegionDataLoader(true);
        axios
            .post('http://localhost:8082/api/jobs/REGION_DATA_IMPORT/execute', {
                filePath: regionDataFilePath,
            })
            .then(async () => {
                setTimeout(() => {
                    axios
                        .get('http://localhost:8082/api/jobs/REGION_DATA_IMPORT')
                        .then(async (response) => {
                            pollingImportStatus(response.data.id);
                        })
                        .catch(() => {
                            setShowRegionDataLoader(false);
                            setShowRegionDataImportStatusFailure(true);
                        });
                }, 4500);
            })
            .catch(() => {
                setShowRegionDataLoader(false);
                setShowRegionDataImportStatusFailure(true);
            });
    };

    return (
        <Row align="middle" className="admin-panel--row">
            <Col span={3} xs={10} sm={3} md={5} xl={3}>
                <div className="align-left">
                    <Upload accept=".csv" customRequest={uploadRegionData} showUploadList={false} maxCount={1}>
                        <Button disabled={showRegionDataLoader} icon={<UploadOutlined />}>
                            Regions upload
                        </Button>
                    </Upload>
                </div>
            </Col>
            <Col span={3} xs={9} sm={3} md={4} xl={2}>
                <div className="align-center">
                    {showRegionDataLoader && <Loader />}
                    {showRegionDataImportBtn && <Button onClick={executeImportData}>Import data</Button>}
                    {showRegionDataImportStatusSuccess && (
                        <span className="admin-panel--import-data__success">Success</span>
                    )}
                    {showRegionDataImportStatusFailure && (
                        <span className="admin-panel--import-data__failure">Failure</span>
                    )}
                </div>
            </Col>
        </Row>
    );
};

export default RegionDataImport;
